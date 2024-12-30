package com.example.engineering;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.example.engineering.Model.Account;
import com.example.engineering.Model.Product;
import com.example.engineering.Model.oder;
import com.example.engineering.Reponsi.oderReponse;

@SpringBootApplication
public class EngineeringApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;
	@Autowired
	oderReponse oderreponse;
	public static void main(String[] args) {
		SpringApplication.run(EngineeringApplication.class, args);
	}
	@Override
    public void run(String... args) throws Exception {
        // Kiểm tra kết nối đến database
			String sql = "SELECT * FROM PRODUCTs";
			String sql1 = "SELECT * FROM Account";

			try {
				// Sử dụng JdbcTemplate để thực hiện truy vấn và map kết quả vào đối tượng
				List<Product> Products = jdbcTemplate.query(sql, new RowMapper<Product>() {
					@Override
					public Product mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
						// Giả sử bảng `Product` có các cột `id`, `username`, `password`
						Product Product = new Product(rs.getString(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getString(8),
						rs.getString(9));
						
						return Product;
					}
				});
				// In kết quả ra console
				for (Product Product : Products) {
					System.out.println(Product);
				}
				List<Account> Accounts=jdbcTemplate.query(sql1,new RowMapper<Account>() {
					@Override
					public Account mapRow(java.sql.ResultSet rs,int rowNum)throws java.sql.SQLException {
					Account as=new Account(rs.getString(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4),
					rs.getString(5),
					rs.getInt(6),
					rs.getString(7) );
					
						return as;
				}
				});
				System.out.println("-------------------List Account-------------------\n--------------------------------------------------");
				for (Account Product :Accounts) {
					System.out.println(Product);
				}
				List<oder>listll=new ArrayList<>();
				listll=oderreponse.getAlllistoder();
				for (oder Product :listll) {
					System.out.println(Product);
				}
        } catch (Exception e) {
            System.err.println("Không thể kết nối đến cơ sở dữ liệu: " + e.getMessage());
        }
		}
}
