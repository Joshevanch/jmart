package com.joshevanJmartFA.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.*;
import com.joshevanJmartFA.*;
import com.joshevanJmartFA.Payment.Record;
import com.joshevanJmartFA.dbjson.*;

/**
 * This class will be the {@link Payment} controller between back end and front end
 * @author Joshevan
 *
 */
@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController <Payment>{
	/**
	 * Delivered constant time
	 */
	public static final long DELIVERED_LIMIT_MS = 5000;
	/**
	 * On delivery constant time
	 */
	public static final long ON_DELIVERY_LIMIT_MS = 10000;
	/**
	 * On progress constant time
	 */
	public static final long ON_PROGRESS_LIMIT_MS = 20000;
	/**
	 * Waiting confirmation constant time
	 */
	public static final long WAITING_CONF_LIMIT_MS = 30000;
	/**
	 * Payment Table from Json Table in the local directory
	 */
	public static @JsonAutowired(filepath = "D:\\Tugas\\Semester 3\\Pemrograman Berorientasi Objek\\Praktikum\\jmart\\JSON\\payment.json", value = Payment.class) JsonTable<Payment> paymentTable;
	/**
	 * pool thread for multi threading
	 */
	public static ObjectPoolThread<Payment> poolThread;
	/**
	 * Create and start multi threading
	 */
	static {
		poolThread = new ObjectPoolThread<Payment>("name", PaymentController::timekeeper);
		poolThread.start();
	}
	/**
	 * Getter method for payment table
	 */
	public JsonTable<Payment> getJsonTable(){
		return PaymentController.paymentTable;
	}
	/**
	 * This method accepts payment from front end
	 * @param id payment id
	 * @return true if payment accepted, return false if failed to accept payment
	 */
	@PostMapping("/{id}/accept")
	boolean accept (@PathVariable int id) {
		/**
		 * Find payment with same id 
		 */
		Payment payment = Algorithm.<Payment>find(paymentTable, a->a.id == id);
		if (payment != null && payment.history.get(payment.history.size() - 1).status == Invoice.Status.WAITING_CONFIRMATION) {
			payment.history.add(new Record (Invoice.Status.ON_PROGRESS, "On Progress"));
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * This method create new payment from front end
	 * @param buyerId buyer id 
	 * @param productId product id 
	 * @param productCount number of product
	 * @param shipmentAddress shipment address
	 * @param shipmentPlan shipment plan
	 * @return payment if payment created, null if failed to create payment
	 */
	@PostMapping("/create")
	Payment create (@RequestParam int buyerId, @RequestParam int productId, @RequestParam int productCount, @RequestParam String shipmentAddress, @RequestParam byte shipmentPlan) {
		/**
		 * Find account with same id 
		 */
		Account account = Algorithm.<Account>find(AccountController.accountTable, a-> a.id == buyerId);
		/**
		 * Find product with product id
		 */
		Product product = Algorithm.<Product>find(ProductController.productTable, a-> a.id == productId);
		/**
		 * Create new payment 
		 */
		Payment payment = new Payment (buyerId, productId, productCount, new Shipment (shipmentAddress, 0, shipmentPlan, null));
		double price = productCount*payment.getTotalPay(product);
		if (account != null && product != null && account.balance >= price && product.accountId != buyerId) {
			account.balance = account.balance - price;
			payment.history.add(new Record (Invoice.Status.WAITING_CONFIRMATION, "Waiting Confirmation"));
			paymentTable.add(payment);
			poolThread.add(payment);
			return payment;
		}	
		else {
			return null;
		}
	}
	/**
	 * This method cancel payment from front end
	 * @param id payment id 
	 * @return true if payment cancelled, false if failed to cancel payment 
	 */
	@PostMapping("/{id}/cancel")
	boolean cancel (@PathVariable int id) {
		/**
		 * Find payment with same id 
		 */
		Payment payment = Algorithm.<Payment>find(paymentTable, a->a.id == id);
		if (payment != null && payment.history.get(payment.history.size() - 1).status == Invoice.Status.WAITING_CONFIRMATION) {
			payment.history.add(new Record (Invoice.Status.CANCELLED, "Cancelled"));
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * This method submit payment from front end
	 * @param id payment id 
	 * @param receipt payment receipt
	 * @return true if payment submitted, false if failed to submit payment 
	 */
	@PostMapping("/{id}/submit")
	boolean submit (@PathVariable int id, @RequestParam String receipt) {
		/**
		 * Find payment with same id
		 */
		Payment payment = Algorithm.<Payment>find(paymentTable, a->a.id == id);
		if (payment != null && payment.history.get(payment.history.size() - 1).status == Invoice.Status.WAITING_CONFIRMATION && receipt.isBlank()== false) {
			payment.shipment.receipt = receipt;
			payment.history.add(new Record (Invoice.Status.ON_DELIVERY, "On Delivery"));
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * This method collects list of buyer payment
	 * @param id buyer id 
	 * @param page page number
	 * @param pageSize number of object
	 * @return list of buyer payment 
	 */
	@GetMapping("/{id}/buyerpayment")
	List <Payment> getPaymentById (@PathVariable int id, @RequestParam int page, @RequestParam int pageSize){
		return Algorithm.<Payment>paginate(paymentTable, page,pageSize, a ->a.buyerId==id);
	}
	/**
	 * This method collects list of store payment
	 * @param id account id 
	 * @param page page number 
	 * @param pageSize number of object
	 * @return list of store payment
	 */
	@GetMapping("/{id}/storepayment")
	List <Payment> getPaymentByProduct (@PathVariable int id, @RequestParam int page, @RequestParam int pageSize){
		List <Product> storeProduct = Algorithm.<Product>collect(ProductController.productTable, a->a.accountId==id);
		List <Payment> storePayment  = new ArrayList <Payment>();
		List <Payment> storePayment2 = new ArrayList <Payment>();
		int i = 0;
		for (Payment payment : paymentTable) {
			for (Product product : storeProduct) {
				if (payment.productId == product.id) {
					storePayment.add(payment);
				}
			}
		}
		for (Payment b : storePayment) {
			if (i>= ((page)*pageSize) && i<((page+1)*pageSize)) {
				storePayment2.add(b);
			}
			i ++;
}
    	return storePayment2;
	}
	
	/**
	 * This method timekeep the payment 
	 * @param payment payment
	 * @return true after timekeep success
	 */
    public static boolean timekeeper (Payment payment) {
    		Date date = new Date();
        	long difftime = payment.history.get(payment.history.size() - 1).date.getTime() - date.getTime();
        	long diff =	TimeUnit.MILLISECONDS.toMillis(difftime);
    	if (payment.history.get(payment.history.size() - 1).status == Invoice.Status.WAITING_CONFIRMATION && diff >WAITING_CONF_LIMIT_MS ) {
    		payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Pengiriman gagal"));
    	}
    	else if (payment.history.get(payment.history.size() - 1).status == Invoice.Status.ON_PROGRESS && diff >ON_PROGRESS_LIMIT_MS) {
    		payment.history.add(new Payment.Record(Invoice.Status.FAILED, "Pengiriman gagal"));
    	}
    	else if (payment.history.get(payment.history.size() - 1).status == Invoice.Status.ON_DELIVERY && diff >ON_DELIVERY_LIMIT_MS) {
    		payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "Pengiriman berhasil"));
    	
    	}
    	else if (payment.history.get(payment.history.size() - 1).status == Invoice.Status.ON_PROGRESS && diff >DELIVERED_LIMIT_MS) {
    		payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "Pengiriman berhasil"));
    		
    	}	
    	return true;
    }
}
