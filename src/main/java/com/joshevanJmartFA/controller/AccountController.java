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
/**
 * This class will be the account controller between back end and front end
 * @author Joshevan
 *
 */
public class AccountController implements BasicGetController <Account>
{
	/**
	 * Regex email constant for checking
	 */
	public static final String REGEX_EMAIL = "^(?!.*^[.])(?!.*[.]{2})[a-zA-Z0-9&._*~]+@(?![.-])[a-zA-Z0-9.-]+(?!.*$[.])$";
	/**
	 * Regex password constant for checking
	 */
	public static final String REGEX_PASSWORD = "^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?!.*[\\s]{2}).*$";
	/**
	 * Regex pattern for matching
	 */
	public static final Pattern REGEX_PATTERN_EMAIL = Pattern.compile (REGEX_EMAIL);
	/**
	 * Regex pattern for matching
	 */
	public static final Pattern REGEX_PATTERN_PASSWORD = Pattern.compile (REGEX_PASSWORD);
	
	/**
	 * Account Table from Json Table in the local directory
	 */
	public static @JsonAutowired(filepath = "JSON\\account.json", value = Account.class) JsonTable<Account> accountTable;
	/**
	 * Getter method for Account Json Table
	 */
	public JsonTable<Account> getJsonTable(){
		return AccountController.accountTable;
	}
	@PostMapping("/login")
	/**
	 * This method used for account login from front end
	 * @param email account email
	 * @param password account password
	 * @return true if account exists, null if account does not exist
	 */
	Account login (@RequestParam String email, @RequestParam String password) {
		/**
		 * Find account with same email
		 */
		Account account = Algorithm.<Account>find(accountTable, a -> a.email.equals(email));
		String generatedPassword = null;
		/**
		 * Hash password with MD5
		 */
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
	if (account != null && account.password.equals(generatedPassword) ) {
			return account;
		}
		else {
			return null;
		}
	}
	/**
	 * This method used for account register from front end
	 * @param name account name
	 * @param email account email
	 * @param password account password
	 * @return account if email and password matches regex, return null if failed to create account
	 */
	@PostMapping("/register")
	Account register (@RequestParam String name, @RequestParam String email,@RequestParam  String password) {
		/**
		 * Regex matcher with input email
		 */
		Matcher matcherEmail = REGEX_PATTERN_EMAIL.matcher(email);
		/**
		 * Regex matcher with input password
		 */
	    Matcher matcherPass = REGEX_PATTERN_PASSWORD.matcher(password);
	    if (name.isBlank() || ((Algorithm.<Account>find(accountTable,account-> account.email.equals(email) )) != null)) {
	    	return null;
	    }
	    else if (matcherEmail.find() && matcherPass.find()) {
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
			accountTable.add(a);
			return a;
	    }
	    else {
	    	return null;
	    }
	}
	/**
	 * This method register store from front end
	 * @param id account id
	 * @param name store name
	 * @param address store address
	 * @param phoneNumber store phone number
	 * @return store if store created, return null if failed to create
	 */
	@PostMapping("/{id}/registerStore")
	Store registerStore (@PathVariable int id,@RequestParam String name,@RequestParam String address,@RequestParam String phoneNumber) {
		/**
		 * Create new store
		 */
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
	/**
	 * This method top up account balance from front end
	 * @param id account id 
	 * @param balance account balance
	 * @return true if success to top up, return null if failed to top up
	 */
	@PostMapping("/{id}/topUp")
	Boolean topUp (@PathVariable int id, @RequestParam double balance) {
		/**
		 * Find account with same id 
		 */
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

