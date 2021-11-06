package joshevanJmartFA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import java.util.ArrayList;

public class Jmart
{
    class Country
    {
    	public String name;
    	public int population;
    	public List<String> listOfStates;
    }
    public static List<Product> filterByCategory (List<Product> list, ProductCategory category){
    	List <Product> filteredByCategoryList = Algorithm.<Product>collect(list, product -> product.category == category);
    	return filteredByCategoryList;
    }
    public static List<Product> filterByPrice (List<Product> list, double minPrice, double maxPrice){
    	List <Product> filteredByPriceList = new ArrayList <Product>();
    	if (maxPrice == 0 & minPrice == 0) {
    		filteredByPriceList = null;
    	}
    	else if (maxPrice == 0) {
    		filteredByPriceList = Algorithm.<Product>collect(list, product -> product.price >= minPrice);
    	}
    	else if (minPrice == 0) {
    		filteredByPriceList = Algorithm.<Product>collect(list, product -> product.price <= maxPrice);
    	}
    	else {
    		filteredByPriceList = Algorithm.<Product>collect(list, product -> product.price>=minPrice && product.price <= maxPrice);
    	}
    	return filteredByPriceList;
    }
   
    public static void main (String[] args) 
    {
    	System.out.println("account id: "+ new Account (null, null, null, -1).id);
    	System.out.println("account id: "+ new Account (null, null, null, -1).id);
    	System.out.println("account id: "+ new Account (null, null, null, -1).id);
    	
    	System.out.println("account id: "+ new Payment (-1, -1, -1, null).id);
    	System.out.println("account id: "+ new Payment (-1, -1, -1, null).id);
    	System.out.println("account id: "+ new Payment (-1, -1, -1, null).id);
    		
    	String filejson = "joshevanJmartFa\\randomProductList.json";
    	try
    	{
    	List <Product> list = read (filejson);
    	List <Product> filtered = filterByPrice (list, 10000.0, 20000.0);
    	filtered.forEach(product -> System.out.println(product.price));
    	}
    	catch (Throwable t)
    	{
    		t.printStackTrace();
    	}
    }
    public static List<Product> read (String filepath) throws FileNotFoundException {
    	JsonReader json = new JsonReader(new FileReader (filepath));
    	Product [] products = new Gson().fromJson(json, Product[].class);
    	List <Product> list = Algorithm.<Product>collect (products, product->true);
    	return list;
    }
    
}

