package com.joshevanJmartFA.controller;
import com.joshevanJmartFA.*;
import com.joshevanJmartFA.dbjson.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**

 * This class will be the {@link Coupon} controller between back end and front end
 * @author Joshevan
 *
 */
@RestController
@RequestMapping("/coupon")
public class CouponController implements BasicGetController <Coupon> {
	/**
	 * Coupon Table from Json Table in the local directory
	 */
	public static @JsonAutowired(filepath = "D:\\Tugas\\Semester 3\\Pemrograman Berorientasi Objek\\Praktikum\\jmart\\JSON\\coupon.json", value = Coupon.class) JsonTable<Coupon> couponTable;
	/**
	 * Getter method for Coupon Json Table
	 */
	public JsonTable <Coupon> getJsonTable(){
		return CouponController.couponTable;
	}
	/**
	 * This method checks coupon used condition from front end
	 * @param id coupon id
	 * @return coupon used condition
	 */
	@GetMapping("/{id}/isUsed")
	boolean isUsed (int id) {
		return Algorithm.<Coupon>find(couponTable, a -> a.id == id).isUsed();
	}
	/**
	 * This method checks whether coupon can be applied or not from front end 
	 * @param id coupon id
	 * @param price product price
	 * @param discount product discount
	 * @return coupon can be applied or not
	 */
	@GetMapping("/{id}/canApply")
	boolean canApply (int id, double price, double discount) {
		return Algorithm.<Coupon>find(couponTable, a -> a.id == id).canApply(price, discount);
	}
	/**
	 * This method collects available  coupon from front end
	 * @param page Page number
	 * @param pageSize Number of object
	 * @return available coupon
	 */
	@GetMapping("/getAvailable")
	List<Coupon> getAvailable (@RequestParam(defaultValue = "5")int page, @RequestParam(defaultValue = "5")int pageSize){
		List<Coupon> availableList = Algorithm.<Coupon>collect(couponTable, coupon ->coupon.isUsed()==false);
		return Algorithm.<Coupon>paginate(availableList,page,pageSize, coupon ->true);
		}
}
