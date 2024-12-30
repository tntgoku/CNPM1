package com.example.engineering.Reponsi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.example.engineering.Model.Product;
import com.example.engineering.Model.oder;
import com.example.engineering.Model.oder;
import com.example.engineering.Model.oder;
@Service
public class oderReponse {
        @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<oder> getAlllistoder(){
        String sql= "select * from order_p";
        try {
            return jdbcTemplate.query(sql, new RowMapper<oder>() {
                @Override
                public oder mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                    return new oder(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getString(6),
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

    public List<oder> Getoder(String IDC){
        String sql="SELECT * FROM order_p where IDC =?";
        try {
            return jdbcTemplate.query(sql,new Object[]{IDC}, new RowMapper<oder>() {
                @Override
                public oder mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                    return new oder(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getString(6),
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

    public void pushData(String l1,String l2,String l3,int i1, int i2,String l4,String l5,String l6, String l7){
        String sql=    "INSERT INTO ORDER_P (IDO, IDC, timer, total, ppm, process,address,phone,name) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, l1,l2,l3,i1,i2,l4,l5,l6,l7);
    }
    // i2= Processs,l1= IDO
    public void updateOder(int i2,String l1){
            String sql="Update ORDER_P set process =? WHERE IDO =? ";
            jdbcTemplate.update(sql,i2,l1);
    }
    public void updateOder(String l1,String l2,String l3,int i1, int i2,String l4,String l5,String l6, String l7){
        String sql=    "UPDATE ORDER_P SET IDC =?, timer = ?,"+
                                    "total =?,ppm = ? ,process = ? ,address =?,phone =?,name =?WHERE IDO = ? values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, l2,l3,i1,i2,l4,l5,l6,l7,l1);
    }

    public List<oder> searchOders(String namesearch){
        String sql = "SELECT * FROM order_ps WHERE Name LIKE ?"; 
       return jdbcTemplate.query(sql, new Object[] { "%" + namesearch + "%" }, new RowMapper<oder>() {
			@Override
			public oder mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
                return new oder(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9)
                    );
			}
		});
    }

    
}
