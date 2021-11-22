package com.joshevanJmartFA.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.joshevanJmartFA.*;
import com.joshevanJmartFA.dbjson.JsonAutowired;
import com.joshevanJmartFA.dbjson.JsonTable;

@RestController
@RequestMapping("/product")
public class ProductController implements BasicGetController<Product> {
	public static @JsonAutowired(filepath = "D:\\Tugas\\Semester 3\\Pemrograman Berorientasi Objek\\Praktikum\\jmart\\src\\main\\java\\com\\joshevanJmartFA\\a\\b\\c\\product.json", value = Product.class) JsonTable<Product> productTable;
	public JsonTable <Product> getJsonTable(){
		return ProductController.productTable;
	}
	@GetMapping("/{id}/store")
	List <Product>getProductByStore (int id, int page, int pageSize){
		return Algorithm.<Product>paginate(productTable, page, pageSize, a ->a.accountId==id);
	}
	@PostMapping("/create")
	Product create (int accountId, String name, int weight, boolean conditionUsed, double price, double discount, ProductCategory category, byte shipmentPlans) {
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
	List <Product>getProductFiltered (int page,int pageSize, int accountId, String search, @RequestParam(defaultValue = "0")int minPrice, int maxPrice, ProductCategory category){
		List<Product> filteredList = Algorithm.<Product>collect(productTable, product -> product.accountId==accountId);
		return Algorithm.<Product>paginate (filteredList, page, pageSize, product -> product.name.matches ("(?i).*" + search + ".*") && product.price>=minPrice && product.price <= maxPrice && product.category == category);
	}
}
