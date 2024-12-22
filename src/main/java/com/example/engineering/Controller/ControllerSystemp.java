package com.example.engineering.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.engineering.Model.Account;
import com.example.engineering.Model.CartItem;
import com.example.engineering.Model.Customer;
import com.example.engineering.Model.Product;
import com.example.engineering.Reponsi.AccountReponse;
import com.example.engineering.Reponsi.CartService;
import com.example.engineering.Reponsi.CustommerReponse;
import com.example.engineering.Reponsi.ProductReponse;

import jakarta.servlet.http.HttpSession;

/*
 * Database đc dùng = sql server 
 * 
 * 
 */

@Controller
public class ControllerSystemp {
    
	@Autowired
    AccountReponse accountlist;
    @Autowired
    ProductReponse productReponse;
    @Autowired
    CustommerReponse custommerrepon;
    @Autowired
private CartService cartService;
    @GetMapping("trangchu")
    public String getHomepage(Model model,Model model1,Model model2) {
        List<Account> accounts = accountlist.getallAccounts();
        List<Product> Products=productReponse.getallProduct();
        model.addAttribute("accounts", accounts);
        model1.addAttribute("products", Products);
        return "index";
    }
    
    @GetMapping("details")
    public String getProductDetail(@RequestParam("masanpham") String id, Model model) {
        
        Product product = productReponse.getProductById(id);
        
        // Kiểm tra sản phẩm có tồn tại hay không
        if (product == null) {
            return "error"; // Trả về trang lỗi nếu không tìm thấy sản phẩm
        }
    
        // Thêm sản phẩm vào model để hiển thị trên trang details.html
        model.addAttribute("product", product);
        return "details";
    }

    @GetMapping("dangnhap")
    public String getLoginpage() {
        return "/login/login";
    }


    @GetMapping("customerr")
    public String getCustomerPage() {
        return "admin/customer";
    }

    
    @ModelAttribute("customer")
    public Customer getLoggedInCustomer(HttpSession session) {
        return (Customer) session.getAttribute("customer");
    }

    // @GetMapping("cart")
    // public String getCartPage() {
    //     return "cart";
    // }
    


//Cái này test login Failt thi ko trả về /login/login
// Xử lý đăng nhập (POST request)
@PostMapping("/processLogin")
public String login(@RequestParam("emails") String email,
                    @RequestParam("pwd") String password,
                    RedirectAttributes redirectAttributes, Model
                    model,
                    HttpSession session) {
    try {
        // Authenticate user
        if (accountlist.authenticateUser(email, password)) {
            Account account = accountlist.getAccount("SELECT * FROM ACCOUNT WHERE email = ? and password = ?", email, password);
            
                Customer customer = custommerrepon.findUser(account.getIDuser());
            System.out.print("Login Success\n\n\n\n\n");
            // save infor customer in session

            session.setAttribute("customer", customer);

            return "redirect:/trangchu";
        } else {
            System.out.print("Login Failer3\n\n\n\n\n");
            model.addAttribute("error", "Invalid email or password");
            // redirectAttributes.addFlashAttribute("error", "Invalid email or password");
            return "redirect:/login/login";
        }
    } catch (Exception e) {
        // Xử lý lỗi
        System.out.print("Login Failer4\n\n\n\n\n");
        redirectAttributes.addFlashAttribute("error", "An error occurred during login.");
        return "redirect:/login/login";
    }
}

    


@GetMapping("/logout")
public String logout(HttpSession session) {
    session.invalidate(); // Xóa toàn bộ session
    return "redirect:/trangchu";
}
    @GetMapping("Dashboard")
    public String getDashboardpage() {

        return "admin/dashboar.html";
    }
    

    @GetMapping("/mainProduct")
    public String handleSearch(@RequestParam(name = "search", required = false) String searchQuery, Model model) {
        List<Product> products;

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            // Tìm kiếm sản phẩm theo tên
            products = productReponse.searchProductsByName(searchQuery);
        } else {
            // Lấy tất cả sản phẩm nếu không có từ khóa tìm kiếm
            products = productReponse.getallProduct();
        }

        model.addAttribute("productss", products);
        model.addAttribute("searchQuery", searchQuery); 
        // Trả về view
        return "admin/mainProduct";  
    }


@PostMapping("/addToCart")
public String addToCart(@RequestParam String productId, 
                        @RequestParam int quantity, 
                        Model model,
                        HttpSession session) {
    List<CartItem> cart = cartService.addToCart(session, productId, quantity, productReponse);
    model.addAttribute("cart", cart);
    session.setAttribute("Cartss", cartService.sessions.getAttribute("cart"));
    return "redirect:/cart";
}

@GetMapping("/cart")
public String getCartPage(HttpSession session, Model model) {
    // Get Cartss from session
    List<CartItem> cart = (List<CartItem>) session.getAttribute("Cartss");  
    // Add cart to the model
    model.addAttribute("cart", cart); 
    // Return the cart page template
    return "cart";  
}
}
