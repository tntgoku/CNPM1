package com.example.engineering.Reponsi;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.example.engineering.Model.Account;
import com.example.engineering.Model.Customer;
@Service
public class AccountReponse {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Customer customer;
    public Account account;
    public boolean authenticateUser(String email, String password) {
        String sql = "SELECT COUNT(*) FROM Account WHERE email = ? AND password = ?";
        int count = jdbcTemplate.queryForObject(sql, new Object[]{email, password}, Integer.class);
     
        return count > 0;
    }
    public Account getAccount(String sql, String email, String password) {
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email, password}, (rs, rowNum) -> 
                new Account(
                rs.getString(1),         // Adjust column names as per your database schema
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                rs.getInt(6))
            );
        } catch (Exception e) {
            System.err.println("Error fetching account: " + e.getMessage());
            return null; // Return null if account is not found
        }
    }


    public List<Account> getallAccounts(){
         String sql = "SELECT * FROM account";

        try {
            return jdbcTemplate.query(sql, new RowMapper<Account>() {
                @Override
                public Account mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                    Account account = new Account();
                    account.setIDA(rs.getString("IDA"));
                    account.setIDuser(rs.getString("IDuser"));
                    account.setUsername(rs.getString("username"));
                    account.setPassword(rs.getString("password"));
                    account.setEmail(rs.getString("email"));
                    account.setRole(rs.getInt("role"));
                    System.out.println("Lay thanh cong data");
                    return account;
                }
            });
        } catch (Exception e) {
            System.err.println("Không thể truy vấn cơ sở dữ liệu: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
