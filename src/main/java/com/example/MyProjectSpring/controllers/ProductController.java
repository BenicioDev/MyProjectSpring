package com.example.MyProjectSpring.controllers;

import com.example.MyProjectSpring.dtos.ProductRecordDTO;
import com.example.MyProjectSpring.models.ProductModel;
import com.example.MyProjectSpring.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired // ↓↓↓↓ Ponto de injeção da interface
    ProductRepository productRepository;

    //Records → DTO (Data Tranfer Object) para receber dados ↓
    @PostMapping("/products") //Modificadores de acesso ↓↓↓↓↓
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDTO productRecordDTO){
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDTO, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

}
