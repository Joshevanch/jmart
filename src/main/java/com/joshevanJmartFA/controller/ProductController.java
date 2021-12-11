package com.joshevanJmartFA.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.joshevanJmartFA.*;
import com.joshevanJmartFA.dbjson.JsonAutowired;
import com.joshevanJmartFA.dbjson.JsonTable;

@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product> {
	public static @JsonAutowired(filepath = "D:\\Tugas\\Semester 3\\Pemrograman Berorientasi Objek\\Praktikum\\jmart\\src\\main\\java\\com\\joshevanJmartFA\\a\\b\\c\\randomProductList.json", value = Product.class) JsonTable<Product> productTable;
	public JsonTable <Product> getJsonTable(){
		return ProductController.productTable;
	}
	@GetMapping("/{id}/store")
	List <Product>getProductByStore (@PathVariable int id, int page, int pageSize){
		return Algorithm.<Product>paginate(productTable, page, pageSize, a ->a.accountId==id);
	}
	@PostMapping("/create")
	Product create (@RequestParam int accountId, @RequestParam String name, @RequestParam int weight, @RequestParam boolean conditionUsed,@RequestParam double price, @RequestParam double discount, @RequestParam ProductCategory category, @RequestParam byte shipmentPlans) {
		Product product = new Product (accountId, name, weight, conditionUsed, price, discount, category, shipmentPlans);
		Account account = Algorithm.<Account>find(AccountController.accountTable, a -> a.id == accountId);
		if (account != null && account.store != null ) {
			productTable.add(product);
			return product;
		}
		else {
			return null;
		}
	}
	@GetMapping("/getFiltered")
	List <Product>getProductFiltered (@RequestParam int page,@RequestParam(defaultValue = "10")int pageSize, @RequestParam(defaultValue = "0")int accountId, @RequestParam String search, @RequestParam int minPrice, @RequestParam int maxPrice, @RequestParam ProductCategory category, @RequestParam Boolean conditionUsed){
		if (minPrice == 0 && maxPrice == 0 && category == ProductCategory.NONE) {
			return Algorithm.<Product>paginate (productTable, page, pageSize, product -> product.name.matches ("(?i).*" + search + ".*") && product.conditionUsed == conditionUsed);
		}
		else if (minPrice == 0 && maxPrice == 0) {
			return Algorithm.<Product>paginate (productTable, page, pageSize, product -> product.name.matches ("(?i).*" + search + ".*") && product.category == category && product.conditionUsed == conditionUsed);
		}
		else if (category == ProductCategory.NONE) {
			return Algorithm.<Product>paginate (productTable, page, pageSize, product -> product.category == category && product.conditionUsed == conditionUsed);
		}
		else {
			return Algorithm.<Product>paginate (productTable, page, pageSize, product -> product.name.matches ("(?i).*" + search + ".*") && (product.price>=minPrice && product.price <= maxPrice) && product.category == category && product.conditionUsed == conditionUsed);
		}
		}
}
