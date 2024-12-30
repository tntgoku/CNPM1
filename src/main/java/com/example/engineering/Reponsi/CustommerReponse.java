package com.example.engineering.Reponsi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.example.engineering.Model.Customer;
import com.example.engineering.Model.Product;
import com.example.engineering.Model.oder;
@Service
public class CustommerReponse {
            @Autowired
    private JdbcTemplate jdbcTemplate;

    public Customer findUser(String iduser) {
        String sql = "SELECT * FROM CUSTOMER WHERE IDuser = ?";
        
        // Use queryForObject for a single result
        return jdbcTemplate.queryForObject(sql, new Object[]{iduser}, this::mapRowToCustomer);
    }

    public List<Customer> getAlllistCustomer(){
        String sql="SELECT * FROM CUSTOMER ";
             try {
            return jdbcTemplate.query(sql, new RowMapper<Customer>() {
                @Override
                public Customer mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                    return new Customer(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6)
                    );
                }
            });
        } catch (Exception e) {
            System.err.println("Không thể truy vấn cơ sở dữ liệu: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    private Customer mapRowToCustomer(ResultSet rs, int rowNum) throws SQLException {
        return new Customer(
            rs.getString(1),
            rs.getString(2),
            rs.getString(3),
            rs.getInt(4),
            rs.getString(5),
            rs.getString(6)
        );
    }
}
