package catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import catalog.dao.ProductRepository;
import catalog.model.Product;

@Service
public class ProductService implements IProductService{

	@Autowired
	ProductRepository dao;
	
	@Override
	public void addProduct(Product p) {
		dao.insert(p);
		
	}

	@Override
	public Product getProduct(int id) {
		
		return dao.getBydId(id);
	}

	@Override
	public List<Product> getProductsByCategory(int catid) {
		
		return dao.searchByCategory(catid);
	}

	@Override
	public List<Product> getProductsByName(String name) {
		
		return dao.searchByProductName(name);
	}

	@Override
	public void removeProduct(int id) {
		dao.delete(id);
		
	}

	@Override
	public List<Product> searchProducts(Integer catid, String pname) {
		
		return dao.searchByCrtiertia(catid, pname);
	}

}
