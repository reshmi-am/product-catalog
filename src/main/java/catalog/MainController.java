package catalog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import catalog.model.Product;
import catalog.service.IProductService;

@RestController
public class MainController {
	
	@Autowired
	private IProductService service;
	
	/*
	 * Get product by id
	 */
    @RequestMapping("/product/{id}")
    public @ResponseBody Product getProductInfo(@PathVariable("id") int itemid) {
        return service.getProduct(itemid);
    }
    
	/*
	 * Get products by criteria
	 */
    @RequestMapping("/product")
    public @ResponseBody List<Product> getMatchingProducts(
    					@RequestParam(required = false, value = "category") Integer cid,
    					@RequestParam(required = false, value = "product") String pname ){
    	
        return service.searchProducts(cid, pname);
    }
    
    /*
     * Create a new product
     */
    @RequestMapping(value="/product", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody void addItem(@RequestBody Product p) {
    	service.addProduct(p);
    }
    
    /*
     * Remove a product
     */
    @RequestMapping(value="/product/{id}", method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable("id") int itemid) {
    	service.removeProduct(itemid);
    }

}
