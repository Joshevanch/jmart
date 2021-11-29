package com.joshevanJmartFA.controller;
import com.joshevanJmartFA.dbjson.*;
import com.joshevanJmartFA.*;
import java.util.List;
import org.springframework.web.bind.annotation.*;

public interface BasicGetController<T extends Serializable>{
	@GetMapping("/{id}")
	public default T getById (@PathVariable int id) {
		T t = Algorithm.<T>find(getJsonTable(), a -> a.id == id);
		if (t == null) {
			return null;
		}
		else {
			return t;
		}
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public default List<T> getPage (@RequestParam(defaultValue = "5")int page,@RequestParam(defaultValue = "5") int pageSize){
		return Algorithm.paginate(getJsonTable(),page,pageSize,object->true);
	}
	public abstract JsonTable<T> getJsonTable();
}
