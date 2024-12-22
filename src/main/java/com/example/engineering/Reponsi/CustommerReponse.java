package com.example.engineering.Reponsi;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.engineering.Model.Customer;
import com.example.engineering.Model.Product;
@Service
public class CustommerReponse {
            @Autowired
    private JdbcTemplate jdbcTemplate;

    public Customer findUser(String iduser) {
        String sql = "SELECT * FROM CUSTOMER WHERE IDuser = ?";
        
        // Use queryForObject for a single result
        return jdbcTemplate.queryForObject(sql, new Object[]{iduser}, this::mapRowToCustomer);
    }
    
    private Customer mapRowToCustomer(ResultSet rs, int rowNum) throws SQLException {
        return new Customer(
            rs.getString(1),
            rs.getString(2),
            rs.getString(3),
            rs.getInt(4),
            rs.getString(5)
        );
    }
}
