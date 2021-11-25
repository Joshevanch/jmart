package com.joshevanJmartFA.controller;

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
	@PostMapping("/create")
	boolean accept (int id) {
		Payment payment = Algorithm.<Payment>find(paymentTable, a->a.id == id);
		if (payment != null && payment.history.get(payment.history.size() - 1).status == Invoice.Status.WAITING_CONFIRMATION) {
			payment.history.add(new Record (Invoice.Status.ON_PROGRESS, "On Progress"));
			return true;
		}
		else {
			return false;
		}
	}
	@PostMapping("/{id}/accept")
	Payment create (int buyerId, int productId, int productCount, String shipmentAddress, byte shipmentPlan) {
		Account account = Algorithm.<Account>find(AccountController.accountTable, a-> a.id == buyerId);
		Product product = Algorithm.<Product>find(ProductController.productTable, a-> a.id == productId);
		Payment payment = new Payment (buyerId, productId, productCount, new Shipment (shipmentAddress, 0, shipmentPlan, null));
		double price = payment.getTotalPay(product);
		if (account != null && product != null && account.balance >= price) {
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
	boolean cancel (int id) {
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
	boolean submit (int id, String receipt) {
		Payment payment = Algorithm.<Payment>find(paymentTable, a->a.id == id);
		if (payment != null && payment.history.get(payment.history.size() - 1).status == Invoice.Status.ON_PROGRESS && payment.shipment.receipt.isBlank() == true) {
			payment.shipment.receipt = receipt;
			payment.history.add(new Record (Invoice.Status.ON_DELIVERY, "On Delivery"));
			return true;
		}
		else {
			return false;
		}
	}
	private static boolean timekeeper (Payment payment) {
		return Jmart.paymentTimeKeeper(payment);
	}
}
