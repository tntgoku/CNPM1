package com.example.engineering.Controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.engineering.Model.Customer;
import com.example.engineering.Model.Product;
import com.example.engineering.Reponsi.ProductReponse;

import jakarta.servlet.http.HttpSession;


@Controller
public class ControllerAdmin {

        @Autowired
    ProductReponse productReponse;
    @RequestMapping("mainProduct")
    public String GetProductPageAdmin() {
        return "admin/mainProduct.html";
    }
    
    @GetMapping("addPro")
    public String addproPAge() {
        return "admin/mainProducts/addPro";
    }

    @GetMapping("UpdatePro")
    public String UpdateproPage(@RequestParam("masanpham") String id, Model model) {
        
        Product product = productReponse.getProductById(id);
        

        if (product == null) {
            return "error"; 
        }
    
        model.addAttribute("product", product);
        return "admin/mainProducts/Update";
    }
    


    //Process upload product in Database (MS SQLServer)
    @PostMapping("/addPro")
    public String addProduct(
            @RequestParam("IDP") String idProduct,
            @RequestParam("nameP") String productName,
            @RequestParam("type_id") String typeId,
            @RequestParam("cost") String cost,
            @RequestParam("Quantity") String amount,
            @RequestParam("discount") String discount,
            @RequestParam("img") MultipartFile image,
            @RequestParam("desc") String descc,
            RedirectAttributes redirectAttributes) {
        
        try {
            // Save product details
            Product product = new Product();
            product.setIDP(idProduct);
            product.setNameP(productName);
            product.setIDCate(Integer.parseInt(typeId));
            //COST
            product.setQuantity(Integer.parseInt(amount));
            product.setAmount(Integer.parseInt(cost));
            product.setDamount(Integer.parseInt(discount));
            product.setDesp(descc);

            
            // Handle image upload (save the image to a directory)
            String imageName = image.getOriginalFilename();
            System.out.println("\n\n\n\n"+imageName);
            String uploadDirectory = "/assets/img/icon"; // Define the upload path here

            
            // Set the image path for the product
            product.setImg( uploadDirectory +"/" + imageName);
            product.setTimer(java.time.LocalDate.now().toString());
                System.out.println("]n\n"+product.toString());
                System.out.println(java.time.LocalDate.now().getYear()+"\n"+java.time.LocalDate.now());
                
            // Save the product to the database (using a service or repository)
            productReponse.saveProduct(new Product(
                idProduct,productName,Integer.parseInt(typeId),Integer.parseInt(amount),
                Integer.parseInt(cost),Integer.parseInt(discount),descc,uploadDirectory +"/" + imageName,
                java.time.LocalDate.now().toString()

            ));
            
            redirectAttributes.addFlashAttribute("success", "Product added successfully!");
            return "redirect:/mainProduct"; // Redirect to a product list or another page

        }  catch (Exception e) {
            System.out.println("loi 2");
            redirectAttributes.addFlashAttribute("error", "Error adding product!");
            return "redirect:/addPro"; // Redirect back to the form if there's an error
        }
    }
  
    // @GetMapping("thanhtoan/")
    // public String getMethodName(@RequestParam ("masanpham") String idpro,Model model) {
        
    //     Product product = productReponse.getProductById(idpro);
        

    //     if (product == null) {
    //         return "error"; 
    //     }
    
    //     model.addAttribute("product", product);
    //     return "paymentmem.";
    // }
    
    // @GetMapping("camonbandamuahang")
    // public String ThanksPage() {
    //     return "thanksuser";
    // }
    @RequestMapping(value="/camonbandamuahang",method=RequestMethod.POST)
    public String ThanksPage(@RequestParam("name") String name,
                             @RequestParam("phone") String phone,
                             @RequestParam("address") String address,
                             @RequestParam("paymentMethod") String paymentMethod,
                             @RequestParam(value = "note", required = false) String note,
                             @RequestParam(value = "VAT", required = false) Boolean vat,
                             RedirectAttributes redirectAttributes,Model model,HttpSession session) {

        // Xử lý thông tin từ form
        // Thí dụ: lưu thông tin vào database hoặc thực hiện các log
        Customer a =(Customer) session.getAttribute("customer");
        if(a==null){
            String o="O0001";
            String idp="C0001";
            String  ida="A001";
            String timer=LocalDateTime.now().toString();
            
        }
         model.addAttribute("successMessage", "Cảm ơn bạn đã đặt hàng!")     ;               
        return "thanksuser";  // Điều hướng đến trang cảm ơn
    }
}
