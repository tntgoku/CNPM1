package com.example.engineering.Controller;

import java.time.LocalDate;
import java.time.LocalTime;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.engineering.Model.Account;
import com.example.engineering.Model.CartItem;
import com.example.engineering.Model.CartitemS;
import com.example.engineering.Model.Customer;
import com.example.engineering.Model.Product;
import com.example.engineering.Reponsi.AccountReponse;
import com.example.engineering.Reponsi.CartService;
import com.example.engineering.Reponsi.CustommerReponse;
import com.example.engineering.Reponsi.ProductReponse;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;



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
    Customer a;
    Account b;
    Boolean checkEmail=false;
    @Autowired
    private CartService cartService;
    private List<Account> ListAccount=new ArrayList<>();
    private List<Product> listProducts=new ArrayList<>();
    private List<CartItem> listCart=new ArrayList<>();
    HttpSession mysession;
    @GetMapping("trangchu")
    public String getHomepage(Model model,Model model1,Model model2) {
        List<Account> accounts = accountlist.getallAccounts();
        ListAccount=accountlist.getallAccounts();
        listProducts=productReponse.getallProduct();
        model.addAttribute("accounts", accounts);
        model1.addAttribute("products", listProducts);
        return "index";
    }
    
    @GetMapping("details")
    public String getProductDetail(@RequestParam("masanpham") String id, Model model) {
        
        Product product = productReponse.getProductById(id);
        

        if (product == null) {
            return "error"; 
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
    @PostMapping("/dangnhap")
    public String login(@RequestParam("emails") String email,
                        @RequestParam("pwd") String password,
                        RedirectAttributes redirectAttributes, Model
                        model,
                        HttpSession session) {
        try {
            // Authenticate user
            if(email.isEmpty() || password.isEmpty()){
                if(email.isEmpty()){
                    redirectAttributes.addFlashAttribute("error-email","Vui lòng nhập Email vào đây");
                }
                if(password.isEmpty()){
                    redirectAttributes.addFlashAttribute("error-pwd","Vui lòng nhập Mật khẩu vào đây");
                }
                return "redirect:/login/login";
            }else{

            
                if (accountlist.authenticateUser(email, password)) {
                    Account account = accountlist.getAccount("SELECT * FROM ACCOUNT WHERE email = ? and password = ?", email, password);
                        b=account;
                        Customer customer = custommerrepon.findUser(account.getIDuser());
                        a=customer;
                        account.setTimer(LocalDate.now()+"-"+LocalTime.now());
                        accountlist.UpdateTimer(account);
                    System.out.print("Login Success\n\n\n\n\n");
                    // save infor customer in session

                    session.setAttribute("customer", customer);
                    session.setAttribute("IDCustomers", customer.getID());
                        System.out.println("\n\n\n\n\n "+session.getAttribute("customer").toString());
                    return "redirect:/trangchu";
                } else {
                    System.out.print("Login Failer3\n\n\n\n\n");
                    // session.setAttribute("error", "Tai khoan hoac mat khau ko chinh xac");
                    model.addAttribute("error", "Tài khoản hoặc mật khẩu không chính xác!!!");
                    return "login/login.html";
                }    
            }
        } catch (Exception e) {
            // Xử lý lỗi
            System.out.print("Login Failer4\n\n\n\n\n");
            redirectAttributes.addFlashAttribute("error", "An error occurred during login.");
            return "redirect:/login/login";
        }
    }

    @PostMapping("/register")
    public String Registers(@RequestParam("NameC") String name,
                            @RequestParam("email") String email,
                            @RequestParam("address") String address,
                            @RequestParam("phonenumber") String phone,
                            @RequestParam("password") String pwd,
                            @RequestParam("password1") String pwd1,
                            RedirectAttributes redirectAttributes) {
    
        if (!pwd.equals(pwd1)) {
            redirectAttributes.addFlashAttribute("errors-pwds", "Mật khẩu không khớp, vui lòng nhập lại!");
            return "redirect:/login/login";
        }
    
        for (Account ac : ListAccount) {
            if (ac.getEmail().equals(email)) {
                redirectAttributes.addFlashAttribute("errors-pwds", "Email này đã tồn tại, Vui lòng nhập Email khác!");
                return "redirect:/login/login";
            }
        }
    
        // Thêm tài khoản mới
        String idnew = ListAccount.get(ListAccount.size() - 1).getIDA();
        userNEWId(idnew); // Giả sử userNEWId() xử lý thêm user
    
        redirectAttributes.addFlashAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
        return "redirect:/login/login";
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

   
    // @PostMapping("/details")
    @PostMapping("/details")
    public ResponseEntity<String> addToCart(@RequestBody Map<String, Object> requestBody, HttpSession session) {
        // Lấy thông tin từ request body
        String IDP = (String) requestBody.get("IDP");
        int quantity = (int) requestBody.get("quantity");
    
        // Lấy sản phẩm từ IDP
        Product temp = productReponse.getProductById(IDP);
        if (temp == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sản phẩm không tồn tại.");
        }
    
        // Tạo một đối tượng CartItem
        CartItem tempitem = new CartItem(temp, quantity);
        Customer sCustomer=(Customer)session.getAttribute("customer");

        // Lấy giỏ hàng từ session (nếu có)
        if(sCustomer!=null){
            System.out.println("\n\n\n\n\n\n "+sCustomer.toString());
            String idCart=cartService.findCartUser(session.getAttribute("IDCustomers").toString());
            if(idCart.isEmpty()){
                
            }
            List<HashMap<String,Object>> listcarts=cartService.getProductCart(idCart);
            for (HashMap<String,Object> hashMap : listcarts) {
                Product s=productReponse.getProductById(hashMap.get("IDP").toString());

            }
            List<CartItem> cartList = (List<CartItem>) session.getAttribute("listCart");
        
            // Nếu giỏ hàng chưa có, khởi tạo giỏ hàng mới
            if (cartList == null) {
                cartList = new ArrayList<>();
            }
        
            boolean productExists = false;
        
            // Kiểm tra sản phẩm đã có trong giỏ hàng chưa
            for (CartItem item : cartList) {
                if (item.getProductItem().getIDP().equals(IDP)) {
                    // Nếu có, tăng số lượng
                    item.setQuantity(item.getQuantity() + quantity);
                    productExists = true;
                    break;
                }
            }
        
            // Nếu sản phẩm chưa có trong giỏ hàng, thêm mới
            if (!productExists) {
                cartList.add(tempitem);
            }
            // Lưu lại giỏ hàng vào session
            session.setAttribute("listCart", cartList);
        }else{
            System.out.println("Error\n Nguoi dung chua dang nhap");
            List<CartItem> cartList = (List<CartItem>) session.getAttribute("listCart");
        
            // Nếu giỏ hàng chưa có, khởi tạo giỏ hàng mới
            if (cartList == null) {
                cartList = new ArrayList<>();
            }
        
            boolean productExists = false;
        
            // Kiểm tra sản phẩm đã có trong giỏ hàng chưa
            for (CartItem item : cartList) {
                if (item.getProductItem().getIDP().equals(IDP)) {
                    // Nếu có, tăng số lượng
                    item.setQuantity(item.getQuantity() + quantity);
                    productExists = true;
                    break;
                }
            }
        
            // Nếu sản phẩm chưa có trong giỏ hàng, thêm mới
            if (!productExists) {
                cartList.add(tempitem);
            }
            // Lưu lại giỏ hàng vào session
            session.setAttribute("listCart", cartList);
        }
    
    
        // Trả về thông báo
        return ResponseEntity.ok("Sản phẩm với ID: " + IDP + " và Số lượng: " + quantity + " đã được thêm vào giỏ hàng.");
    }
    
    @GetMapping("/cart")
    public String getCartPage(HttpSession session, Model model) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("listCart");  
        System.out.println(session.getAttribute("listCart").toString());
        model.addAttribute("cart", cart); 
        return "cart";  
    }


    @GetMapping("thanhtoan")
    public String getPaymentPage() {
        return "payment.html";
    }
    public String userNEWId(String ID){
        String words=ID.substring(0,1);
        String numberauto=ID.substring(1 );
        int giaTriMoi = Integer.parseInt(numberauto) + 1;
        // Định dạng lại phần số (giữ 4 chữ số)
        String phanSoMoi = String.format("%04d", giaTriMoi);
        return words+phanSoMoi;
    }
}



