package com.example.engineering.Reponsi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

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

}
