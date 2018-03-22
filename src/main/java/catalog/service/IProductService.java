package catalog.service;

import java.util.List;

import catalog.model.Product;

public interface IProductService {

	void addProduct(Product p);
	
	Product getProduct(int id);
	
	List<Product> getProductsByCategory(int catid);
	
	List<Product> getProductsByName(String pname);
	
	List<Product> searchProducts(Integer catid, String pname);
	
	void removeProduct(int id);
}
