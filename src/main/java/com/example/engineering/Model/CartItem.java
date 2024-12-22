package com.example.engineering.Model;

public class CartItem {// CartItem.java
        private Product product;
        private int quantity;
    
        // Constructors, getters, setters
        public CartItem(Product products, int quantity) {
            this.product= products;
            this.quantity = quantity;
        }
    
        public Product getProductItem() {
            return product;
        }
    
        public void setProductItem(Product productId) {
            this.product = product;
        }
    
    
        public int getQuantity() {
            return quantity;
        }
    
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
        @Override
        public String toString() {
            return "Product ID: " + product.getIDP() +
                   ", Name: " + product.getNameP() +
                   ", Quantity: " + quantity;
        }
}
    
    
