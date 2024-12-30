package com.example.engineering.Reponsi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.engineering.Model.Account;
import com.example.engineering.Model.CartItem;
import com.example.engineering.Model.CartitemS;
import com.example.engineering.Model.Product;

import jakarta.servlet.http.HttpSession;
@Service
public class CartService {

       @Autowired
    private JdbcTemplate jdbcTemplate;
    List<CartItem> listcart=new ArrayList<>();
    
    @Autowired
    ProductReponse productReponse;
    public  List<CartItem> Getlistcart(){
        return listcart;
    }
    @Autowired
    public HttpSession sessions;
    public List<CartItem> addToCart(HttpSession session, String productId, int quantity, ProductReponse productReponse) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
            sessions=session;
        }

        Product producttemp = productReponse.getProductById(productId);
        boolean productExists = false;

        for (CartItem item : cart) {
            if (item.getProductItem().getIDP().equals(producttemp.getIDP())) {
                item.setQuantity(item.getQuantity() + quantity);
                productExists = true;
                break;
            }
        }

        if (!productExists) {
            CartItem newItem = new CartItem(producttemp, quantity);
            cart.add(newItem);
        }

        session.setAttribute("cart", cart);
        sessions=session;
        // Print cart contents to the console
        System.out.println("Cart contents after adding item:");
        for (CartItem item : cart) {
            System.out.println(item);  // Will call toString() method of CartItem
        }

        return cart;
    }
    
    public void addToCart(CartItem cartItem){
            if (listcart==null) {
                listcart=new ArrayList<>();
            }
            boolean checkEqual=false;
            if(!listcart.isEmpty()){
                for (CartItem cartitem : listcart) {
                    if (cartitem.getProductItem().getIDP().equals(cartItem.getProductItem().getIDP())) {
                        checkEqual=true;
                        cartitem.setQuantity(cartitem.getQuantity()+cartItem.getQuantity());
                    }
                }

                if (checkEqual) {
                    listcart.add(cartItem);
                }
            }

            for (CartItem cartItem2 : listcart) {
                System.out.println(cartItem2.toString());
            }
    }
    
  

    
    public String findCartUser(String idUser){
        String sql = "SELECT IDCart FROM CART WHERE IDC= ?";
        try {
            // Query to retrieve the cart ID for the given user
            return jdbcTemplate.queryForObject(sql, new Object[]{idUser}, String.class);
        } catch (EmptyResultDataAccessException e) {
            // If no result is found, return null or some default value indicating no cart exists for the user
            return null;
        }
    }

    public List<HashMap<String, Object>> getProductCart(String IDCart) {
        String sql = "SELECT * FROM CART_DETAIL WHERE IDCart = ?";
        try {
            // Using query to fetch multiple results and map each row to a HashMap
            return jdbcTemplate.query(sql, new Object[]{IDCart}, (rs, rowNum) -> {
                HashMap<String, Object> cartDetailMap = new HashMap<>();
                cartDetailMap.put("STT", rs.getInt("STT"));      
                cartDetailMap.put("IDCart", rs.getString("IDCart")); 
                cartDetailMap.put("IDP", rs.getString("IDP"));   
                cartDetailMap.put("Quantity", rs.getInt("Quantity")); 
                return cartDetailMap;
            });
        } catch (Exception e) {
            System.err.println("Error fetching cart details: " + e.getMessage());
            // Return null if cart details are not found
            return null;  
        }
    }
    
    public int getAllQuantity(){
        int count=0;
        if(!listcart.isEmpty()){
            for (CartItem cartItem : listcart) {
                    count+=cartItem.getQuantity();                
            }
        }
        return count;
    }

    public void createCart(String iduser){
        // jdbcTemplate.query(sql, null, null, null)
    }
    public int countCart(){
        String sql ="SELECT COUNT(*) FROM CART";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
     
        return count ;
    }
    private  boolean checkID(String id){
        String sql="Select count(*) from CART where IDCart= ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
            if (count==0) {
                return false;
            }else{
                return true;
            }
    }
}




