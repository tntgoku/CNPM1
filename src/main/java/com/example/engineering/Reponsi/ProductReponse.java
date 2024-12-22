package com.example.engineering.Reponsi;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.example.engineering.Model.Product;

@Service
public class ProductReponse  {
        @Autowired
    private JdbcTemplate jdbcTemplate;

	public List<Product> getallProduct() {
		String sql = "SELECT * FROM PRODUCTs";
		try {
			return jdbcTemplate.query(sql, new RowMapper<Product>() {
				@Override
				public Product mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
					return new Product(
						rs.getString(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9)
					);
				}
			});
		} catch (Exception e) {
			System.err.println("Không thể truy vấn cơ sở dữ liệu: " + e.getMessage());
			return new ArrayList<>();
		}
	}



	  public Product getProductById(String id) {
        String sql = "SELECT * FROM products WHERE IDP = ?";

        // Dùng queryForObject để lấy một đối tượng từ cơ sở dữ liệu
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, this::mapRowToProduct);
    }

    // Phương thức ánh xạ ResultSet thành đối tượng Product
    private Product mapRowToProduct(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
		product.setIDP( rs.getString(1) ); 
		product.setNameP( rs.getString(2));
		product.setIDCate( rs.getInt(3));
		product.setQuantity(rs.getInt(4));
		product.setAmount (rs.getInt(5));
		product.setDamount (rs.getInt(6));
		product.setDesp (rs.getString(7));
		product.setImg (rs.getString(8));
		product.setTimer (rs.getString(9));
        return product;
    }

	// Phương thức tìm kiếm sản phẩm theo tên
	public List<Product> searchProductsByName(String searchQuery) {
		String sql = "SELECT * FROM products WHERE nameP LIKE ?";
		return jdbcTemplate.query(sql, new Object[] { "%" + searchQuery + "%" }, new RowMapper<Product>() {
			@Override
			public Product mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
				return new Product(
					rs.getString(1),  // IDP
					rs.getString(2),  // NameP
					rs.getInt(3),     // IDCate
					rs.getInt(4),     // Quantity
					rs.getInt(5),     // Amount
					rs.getInt(6),     // Damount
					rs.getString(7),  // Desp
					rs.getString(8),  // Img
					rs.getString(9)   // Timer
				);
			}
		});
	}


	public void saveProduct(Product product){


		System.out.println("Cai nay cua save "+product.toString());
		String sql = "INSERT INTO Products (IDP, nameP, IDCate, Quantity, Amount, Damount, Desp, img, timer) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, product.getIDP(),product.getNameP(),product.getIDCate(),product.getQuantity(),
		product.getAmount(),product.getDamount(),product.getDesp(),
		product.getImg(),product.getTimer()
		);
	}

	public void UpdatePro(Product product){
		String sql = "UPDATE Products SET nameP = ?, IDCate = ?, Quantity = ?, Amount = ?, Damount = ?, Desp = ?, img = ?, timer = ? WHERE IDP = ?";
		jdbcTemplate.update(sql, product.getNameP(),product.getIDCate(),product.getQuantity(),
		product.getAmount(),product.getDamount(),product.getDesp(),
		product.getImg(),product.getTimer(),product.getIDP())
		;
	}

}	
