package com.joshevanJmartFA.controller;

import org.springframework.web.bind.annotation.*;
import com.joshevanJmartFA.*;
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
	boolean create (int id) {
		return true;
	}
	@PostMapping("/{id}/accept")
	boolean accept (int id) {
		return true;
	}
	@PostMapping("/{id}/cancel")
	boolean cancel (int id) {
		return true;
	}
	@PostMapping("/{id}/submit")
	boolean submit (int id) {
		return true;
	}
	private static boolean timekeeper (Payment payment) {
		return Jmart.paymentTimeKeeper(payment);
	}
}
