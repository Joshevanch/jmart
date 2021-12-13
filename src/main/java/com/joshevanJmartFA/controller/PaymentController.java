package com.joshevanJmartFA.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.*;
import com.joshevanJmartFA.*;
import com.joshevanJmartFA.Payment.Record;
import com.joshevanJmartFA.dbjson.*;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController <Payment>{
	public static final long DELIVERED_LIMIT_MS = 10;
	public static final long ON_DELIVERY_LIMIT_MS = 10;
	public static final long ON_PROGRESS_LIMIT_MS = 10;
	public static final long WAITING_CONF_LIMIT_MS = 10;
	public static @JsonAutowired(filepath = "D:\\Tugas\\Semester 3\\Pemrograman Berorientasi Objek\\Praktikum\\jmart\\src\\main\\java\\com\\joshevanJmartFA\\a\\b\\c\\payment.json", value = Payment.class) JsonTable<Payment> paymentTable;
	public static ObjectPoolThread<Payment> poolThread;
	static {
		poolThread = new ObjectPoolThread<Payment>("name", PaymentController::timekeeper);
		poolThread.start();
	}
	public JsonTable<Payment> getJsonTable(){
		return PaymentController.paymentTable;
	}
	@PostMapping("/{id}/accept")
	boolean accept (@PathVariable int id) {
		Payment payment = Algorithm.<Payment>find(paymentTable, a->a.id == id);
		if (payment != null && payment.history.get(payment.history.size() - 1).status == Invoice.Status.WAITING_CONFIRMATION) {
			payment.history.add(new Record (Invoice.Status.ON_PROGRESS, "On Progress"));
			return true;
		}
		else {
			return false;
		}
	}
	@PostMapping("/create")
	Payment create (@RequestParam int buyerId, @RequestParam int productId, @RequestParam int productCount, @RequestParam String shipmentAddress, @RequestParam byte shipmentPlan) {
		Account account = Algorithm.<Account>find(AccountController.accountTable, a-> a.id == buyerId);
		Product product = Algorithm.<Product>find(ProductController.productTable, a-> a.id == productId);
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
	@PostMapping("/{id}/cancel")
	boolean cancel (@PathVariable int id) {
		Payment payment = Algorithm.<Payment>find(paymentTable, a->a.id == id);
		if (payment != null && payment.history.get(payment.history.size() - 1).status == Invoice.Status.WAITING_CONFIRMATION) {
			payment.history.add(new Record (Invoice.Status.CANCELLED, "Cancelled"));
			return true;
		}
		else {
			return false;
		}
	}
	@PostMapping("/{id}/submit")
	boolean submit (@PathVariable int id, String receipt) {
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
	@GetMapping("/{id}/buyerpayment")
	List <Payment> getPaymentById (@PathVariable int id, @RequestParam int page, @RequestParam int pageSize){
		return Algorithm.<Payment>paginate(paymentTable, page,pageSize, a ->a.buyerId==id);
	}
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
