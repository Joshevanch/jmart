package com.joshevanJmartFA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import java.util.ArrayList;
import java.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jmart
{
    public static long DELIVERED_LIMIT_MS = 10;
    public static long ON_DELIVERY_LIMIT_MS = 10;
    public static long ON_PROGRESS_LIMIT_MS = 10;
    public static long WAITING_CONF_LIMIT_MS = 10;
    public static boolean paymentTimeKeeper (Payment payment) {
    	if (payment.history.size()>0) {
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
    	if (payment.history.get(payment.history.size()-1).status == Invoice.Status.FINISHED || payment.history.get(payment.history.size()-1).status == Invoice.Status.FAILED) {
    		return true;
    	}
    	else {
    		return false;
    	}
    	}
    	else {
    		return true;
    	}
    }
    public static void main (String [] args) {
    	SpringApplication.run(Jmart.class, args);
    }
    	/*
    	try {
    		JsonTable <Payment> table = new JsonTable <> (Payment.class, "joshevanJmartFa\\randomPaymentList.json");
    		ObjectPoolThread <Payment> paymentPool = new ObjectPoolThread<Payment> ("Thread--pp", Jmart::paymentTimeKeeper );
    		paymentPool.start();
    		table.forEach(payment -> paymentPool.add(payment));
    		while (paymentPool.size() !=0);
    		paymentPool.exit();
    		while (paymentPool.isAlive());
    		System.out.println("Thread exited successfully");
    		Gson gson = new Gson ();
    		table.forEach(payment ->{
    			String history = gson.toJson(payment.history);
    			System.out.println(history);
    		});
    	}
    	catch (Throwable t) {
    		t.printStackTrace();
    	}
    	*/
    	
   
}

