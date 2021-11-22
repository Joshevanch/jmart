package com.joshevanJmartFA.controller;


import com.joshevanJmartFA.*;
import com.joshevanJmartFA.dbjson.*;
import org.springframework.web.bind.annotation.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/account")
public class AccountController implements BasicGetController <Account>
{
	public static final String REGEX_EMAIL = "^(?!.*^[.])(?!.*[.]{2})[a-zA-Z0-9&._*~]+@(?![.-])[a-zA-Z0-9.-]+(?!.*$[.])$";
	public static final String REGEX_PASSWORD = "^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?!.*[\\s]{2}).*$";
	public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile (REGEX_EMAIL);
	public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile (REGEX_PASSWORD);
	
	public static @JsonAutowired(filepath = "D:\\Tugas\\Semester 3\\Pemrograman Berorientasi Objek\\Praktikum\\jmart\\src\\main\\java\\com\\joshevanJmartFA\\a\\b\\c\\account.json", value = Account.class) JsonTable<Account> accountTable;
	public JsonTable<Account> getJsonTable(){
		return AccountController.accountTable;
	}
	@PostMapping("/login")
	Account login (String email, String password) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte [] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i<bytes.length;i++) {
				sb.append(Integer.toString((bytes[i] & 0xFF) + 0x100,16).substring(1));
			}
			generatedPassword = sb.toString();
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		final String generatedPassword1 = generatedPassword;
	Account account = Algorithm.<Account>find(accountTable, a -> a.email == email && generatedPassword1==a.password);
		if (account == null) {
			return null;
		}
		else {
			return account;
		}
	}
	@PostMapping("/register")
	Account register (String name, String email, String password) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte [] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i<bytes.length;i++) {
				sb.append(Integer.toString((bytes[i] & 0xFF) + 0x100,16).substring(1));
			}
			generatedPassword = sb.toString();
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		Account a = new Account (name, email, generatedPassword, 0);
		Matcher matcherEmail = REGEX_PATTERN_EMAIL.matcher(a.email);
	    Matcher matcherPass = REGEX_PATTERN_PASSWORD.matcher(a.password);
	    if (a.name.isBlank()==false && matcherEmail.find() == true && matcherPass.find()==true) {
	    	return a;
	    }
	    else {
	    	return null;
	    }
	}
	@PostMapping("/{id}/registerStore")
	Store registerStore (int id,String name, String address, String phoneNumber) {
		Store store = new Store (name,address,phoneNumber,0);
		Account account = Algorithm.<Account>find(accountTable, a -> a.id == id);
		if (account == null || account.store !=null) {
			return null;
		}
		else {
			account.store = store;
			return store;
		}
	}
	@PostMapping("/{id}/topUp")
	Boolean topUp (int id, double balance) {
	Account account = Algorithm.<Account>find(accountTable, a -> a.id == id);
	if (account == null) {
		return false;
	}
	else {
		account.balance += balance;
		return true;
	}
	}
}

