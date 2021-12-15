package com.joshevanJmartFA.controller;
import com.joshevanJmartFA.dbjson.*;
import com.joshevanJmartFA.*;
import java.util.List;
import org.springframework.web.bind.annotation.*;

/**
 * This class will be the basic controller between back end and front end
 * @author Joshevan
 *
 */
public interface BasicGetController<T extends Serializable>{
	/**
	 * This method used for access id from front end
	 * @param id object id
	 * @return object 
	 */
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
	/**
	 * This method used to get paginated objects 
	 * @param page page number
	 * @param pageSize size of page
	 * @return paginated objects
	 */
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public default List<T> getPage (@RequestParam int page,@RequestParam(defaultValue = "10") int pageSize){
		return Algorithm.paginate(getJsonTable(),page,pageSize,object->true);
	}
	public abstract JsonTable<T> getJsonTable();
}
