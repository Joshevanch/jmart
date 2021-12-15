package com.joshevanJmartFA;

import java.io.BufferedReader;
import com.joshevanJmartFA.dbjson.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.google.gson.*;	
import java.util.ArrayList;
import java.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class will be the executed class
 * @author Joshevan
 *
 */
@SpringBootApplication
public class Jmart
{
	/**
	 * Main method, run spring application
	 * @param args arguments
	 */
    public static void main (String [] args) {
    	JsonDBEngine.Run(Jmart.class);
    	SpringApplication.run(Jmart.class, args);
    	Runtime.getRuntime().addShutdownHook(new Thread(() -> JsonDBEngine.join()));
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

