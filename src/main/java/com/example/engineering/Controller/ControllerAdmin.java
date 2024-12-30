package com.example.engineering.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.example.engineering.Model.oder;
import com.example.engineering.Reponsi.CustommerReponse;
import com.example.engineering.Reponsi.ProductReponse;
import com.example.engineering.Reponsi.oderReponse;

import jakarta.persistence.criteria.Path;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class ControllerAdmin {

        @Autowired
    ProductReponse productReponse;
    @Autowired
    oderReponse orderReponse;
    @Autowired
    CustommerReponse custommerReponse;

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
    
  @PostMapping("UpdatePro")
public String updateProPage(
        @RequestParam("id_product") String id,
        @RequestParam("name") String name,
        @RequestParam("type_id") String typeId,
        @RequestParam("cost") String cost,
        @RequestParam("amount") String amount,
        @RequestParam("discount") String discount,
        @RequestParam("img") MultipartFile imgFile,
        RedirectAttributes redirectAttributes) {

    try {
        // Fetch existing product
        Product product = productReponse.getProductById(id);
        if (product == null) {
            redirectAttributes.addFlashAttribute("error", "Product not found");
            return "redirect:/mainProduct";
        }

        // Update product fields
        product.setNameP(name);
        product.setIDCate(Integer.parseInt(typeId));
        product.setAmount(Integer.parseInt(cost));
        product.setQuantity(Integer.parseInt(amount));
        product.setDamount(Integer.parseInt(discount));

        // Handle image upload if a new image is provided
        if (!imgFile.isEmpty()) {
            String imageName = imgFile.getOriginalFilename();
            String uploadDir = "uploads/";
            Path uploadPath = (Path) Paths.get(uploadDir);
            // Handle image upload (save the image to a directory)
            System.out.println("\n\n\n\n"+imageName);
            String uploadDirectory = "/assets/img/icon"; // Define the upload path here


            // Set the image path for the product
            product.setImg( uploadDirectory +"/" + imageName);
        }

        // Save the updated product
        productReponse.UpdatePro(product);

        redirectAttributes.addFlashAttribute("success", "Product updated successfully");
        return "redirect:/mainProduct";
    } catch (Exception e) {
        e.printStackTrace();
        redirectAttributes.addFlashAttribute("error", "An error occurred while updating the product");
        return "redirect:/mainProduct";
    }
}

@PostMapping("mainProduct")
public ResponseEntity<String> DeletedProPage(@RequestParam("masanpham") String entity) {
    try {
        // Call service to delete the product by ID
       productReponse.DeletePro(entity);
                return ResponseEntity.ok("Sản phẩm với ID: " + entity + " xoa thanh cong");
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.ok("Sản phẩm với ID: " + entity + " ERRRO"); // Redirect to an error page in case of failure
    }
}
    


    @GetMapping("hoadon")
    public String getOrderPage(@RequestParam(name = "search", required = false) String searchQuery,Model model) {
        List<oder> products;

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            // Tìm kiếm sản phẩm theo tên
            products = orderReponse.searchOders(searchQuery);
        } else {
            products=orderReponse.getAlllistoder();
            // Lấy tất cả sản phẩm nếu không có từ khóa tìm kiếm
        }
        
        model.addAttribute("productss",products );
        return "admin/order";
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
            return "redirect:/mainProduct"; // Redirect back to the form if there's an error
        }
    }

    @RequestMapping(value="/camonbandamuahang",method=RequestMethod.POST)
    public String ThanksPage(@RequestParam("name") String name,
                             @RequestParam("phone") String phone,
                             @RequestParam("address") String address,
                             @RequestParam("paymentMethod") String paymentMethod,
                             @RequestParam(value = "note", required = false) String note,
                             @RequestParam(value = "VAT", required = false) Boolean vat,
                             RedirectAttributes redirectAttributes,Model model,HttpSession session) {


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



    @GetMapping("gotocustomer")
    public String getcustomerPage(Model model) {
        model.addAttribute("customerr", model);
        return "admin/customer";
    }

    
    
}
