package com.example.MyProjectSpring.controllers;

import com.example.MyProjectSpring.dtos.ProductRecordDTO;
import com.example.MyProjectSpring.models.ProductModel;
import com.example.MyProjectSpring.repositories.ProductRepository;
import jakarta.validation.Valid;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    //Salvar no BD
    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDTO productRecordDTO){
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDTO, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    //Listar produtos salvos no BD
    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    //Listar apenas um produto
    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct (@PathVariable (value = "id") UUID id){
        Optional<ProductModel> product0 = productRepository.findById(id);
            if (product0.isEmpty()) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado"); }
            return ResponseEntity.status(HttpStatus.OK).body(product0.get());
    }

    //Update Produto
    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct (@PathVariable (value = "id") UUID id, @RequestBody @Valid
                                                 ProductRecordDTO productRecordDTO){
        Optional<ProductModel> product0 = productRepository.findById(id); //Busca BD
        if (product0.isEmpty()) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado"); }
        var productModel = product0.get();
        BeanUtils.copyProperties(productRecordDTO, productModel);




    }


}
