package com.example.engineering.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.engineering.Reponsi.ProductReponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/mainProduct")
public class ControllerProduct {
    @Autowired
    ProductReponse productreponse;

    @PostMapping("DeletedPro")
    public String DeletedProPage(@RequestParam("masanpham")  String entity) {
        //TODO: process POST request
        productreponse.DeletePro(entity);
        return  "redirect:/mainProduct";
    }
    
}
