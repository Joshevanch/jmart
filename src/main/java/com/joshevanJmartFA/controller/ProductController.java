package com.joshevanJmartFA.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.joshevanJmartFA.*;
import com.joshevanJmartFA.dbjson.JsonAutowired;
import com.joshevanJmartFA.dbjson.JsonTable;

/**
 * This class will be the {@link Product} controller between back end and front end
 * @author Joshevan
 *
 */
@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product> {
	/**
	 * Product Table from Json Table in the local directory
	 */
	public static @JsonAutowired(filepath = "D:\\Tugas\\Semester 3\\Pemrograman Berorientasi Objek\\Praktikum\\jmart\\JSON\\Product.json", value = Product.class) JsonTable<Product> productTable;
	/**
	 * Getter method for Product Json Table
	 */
	public JsonTable <Product> getJsonTable(){
		return ProductController.productTable;
	}
	/**
	 * This method collects store product from front end 
	 * @param id account id 
	 * @param page page number
	 * @param pageSize number of objects
	 * @return list of store product
	 */
	@GetMapping("/{id}/store")
	List <Product>getProductByStore (@PathVariable int id, int page, int pageSize){
		return Algorithm.<Product>paginate(productTable, page, pageSize, a ->a.accountId==id);
	}
	/**
	 * This method create new product from front end
	 * @param accountId account id
	 * @param name product name
	 * @param weight product weight
	 * @param conditionUsed product condition used
	 * @param price product price
	 * @param discount product discount
	 * @param category product category
	 * @param shipmentPlans product shipment plans
	 * @return product if product created, null if failed to create product
	 */
	@PostMapping("/create")
	Product create (@RequestParam int accountId, @RequestParam String name, @RequestParam int weight, @RequestParam boolean conditionUsed,@RequestParam double price, @RequestParam double discount, @RequestParam ProductCategory category, @RequestParam byte shipmentPlans) {
		/**
		 * Create new product
		 */
		Product product = new Product (accountId, name, weight, conditionUsed, price, discount, category, shipmentPlans);
		/**
		 * Find account with same id
		 */
		Account account = Algorithm.<Account>find(AccountController.accountTable, a -> a.id == accountId);
		if (account != null && account.store != null ) {
			productTable.add(product);
			return product;
		}
		else {
			return null;
		}
	}
	/**
	 * This method filter product from front end 
	 * @param page page number
	 * @param pageSize number of object
	 * @param accountId account id 
	 * @param search filter name
	 * @param minPrice filter minimum price
	 * @param maxPrice filter maximum price
	 * @param category filter product category
	 * @param conditionUsed filter product used condition 
	 * @return list of filtered account
	 */
	@GetMapping("/getFiltered")
	List <Product>getProductFiltered (@RequestParam int page,@RequestParam(defaultValue = "10")int pageSize, @RequestParam(defaultValue = "0")int accountId, @RequestParam String search, @RequestParam int minPrice, @RequestParam int maxPrice, @RequestParam ProductCategory category, @RequestParam Boolean conditionUsed){
		if (minPrice == 0 && maxPrice == 0 && category == ProductCategory.NONE) {
			return Algorithm.<Product>paginate (productTable, page, pageSize, product -> product.name.matches ("(?i).*" + search + ".*") && product.conditionUsed == conditionUsed);
		}
		else if (minPrice == 0 && maxPrice == 0) {
			return Algorithm.<Product>paginate (productTable, page, pageSize, product -> product.name.matches ("(?i).*" + search + ".*") && product.category == category && product.conditionUsed == conditionUsed);
		}
		else if (category == ProductCategory.NONE) {
			return Algorithm.<Product>paginate (productTable, page, pageSize, product -> product.name.matches ("(?i).*" + search + ".*") && product.price>=minPrice && product.price <= maxPrice && product.conditionUsed == conditionUsed);
		}
		else {
			return Algorithm.<Product>paginate (productTable, page, pageSize, product -> product.name.matches ("(?i).*" + search + ".*") && product.price>=minPrice && product.price <= maxPrice && product.category == category && product.conditionUsed == conditionUsed);
		}
		}
}
