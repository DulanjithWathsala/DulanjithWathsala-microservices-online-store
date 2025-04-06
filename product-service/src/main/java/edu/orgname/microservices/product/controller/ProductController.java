package edu.orgname.microservices.product.controller;

import edu.orgname.microservices.product.dto.ProductRequest;
import edu.orgname.microservices.product.dto.ProductResponse;
import edu.orgname.microservices.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product/v1")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse create(@RequestBody ProductRequest productRequest) {
        return productService.create(productRequest);
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return productService.getAll();
    }
}
