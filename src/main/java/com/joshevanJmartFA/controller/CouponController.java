package com.joshevanJmartFA.controller;
import com.joshevanJmartFA.*;
import com.joshevanJmartFA.dbjson.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/coupon")
public class CouponController implements BasicGetController <Coupon> {
	public static @JsonAutowired(filepath = "D:\\Tugas\\Semester 3\\Pemrograman Berorientasi Objek\\Praktikum\\jmart\\src\\main\\java\\com\\joshevanJmartFA\\a\\b\\c\\coupon.json", value = Coupon.class) JsonTable<Coupon> couponTable;
	public JsonTable <Coupon> getJsonTable(){
		return CouponController.couponTable;
	}
	@GetMapping("/{id}/isUsed")
	boolean isUsed (int id) {
		return Algorithm.<Coupon>find(couponTable, a -> a.id == id).isUsed();
	}
	@GetMapping("/{id}/canApply")
	boolean canApply (int id, double price, double discount) {
		return Algorithm.<Coupon>find(couponTable, a -> a.id == id).canApply(price, discount);
	}
	@GetMapping("/getAvailable")
	List<Coupon> getAvailable (@RequestParam(defaultValue = "5")int page, @RequestParam(defaultValue = "5")int pageSize){
		List<Coupon> availableList = Algorithm.<Coupon>collect(couponTable, coupon ->coupon.isUsed()==false);
		return Algorithm.<Coupon>paginate(availableList,page,pageSize, coupon ->true);
		}
}
