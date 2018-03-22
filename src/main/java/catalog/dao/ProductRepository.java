package catalog.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import catalog.model.Product;

@Repository
public class ProductRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Product getBydId(int id){
		
		Product p = null;
		try{
			p = (Product) jdbcTemplate.queryForObject(
					"select * from products where id = ?", new Object[] { id }, new ProductRowMapper());
		}catch(EmptyResultDataAccessException e){
			
		}
		return p;

	}

	public List<Product> searchByCrtiertia(Integer cid, String pname){
		
		if(cid == null){
			return jdbcTemplate.query("select * from products where name LIKE ?" , new Object[] {
					"%" + pname + "%"
			},  new ProductRowMapper());
		}else{
				
			return jdbcTemplate.query("select * from products where category = ? and name LIKE ?" , new Object[] {
					cid , "%" + pname + "%"
			},  new ProductRowMapper());
		}
	}

	public List<Product> searchByCategory(int cid){
		
		return jdbcTemplate.query("select * from products where category = ? " , new Object[] {
				cid 
		},  new ProductRowMapper());
	}

	
	public List<Product> searchByProductName(String pname){
		
		return jdbcTemplate.query("select * from products where name like ?" , new Object[] {
				"%" + pname + "%"
		},  new ProductRowMapper());
	}

	public int insert(Product product) {

		return jdbcTemplate.update("insert into products (category, name, description, price) " + "values(?, ?, ?, ?)",

				new Object[] {
						product.getCategory(), product.getName(), product.getDescription(), product.getPrice()
		});
	}

	public void delete(int productid){
		 jdbcTemplate.update("delete from products where id = ?" , productid);
	}
}
