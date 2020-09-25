package com.trolit.github.grocerystore.controllers.product;

import com.trolit.github.grocerystore.dto.product.ProductQueryDto;
import com.trolit.github.grocerystore.services.product.ProductQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/products")
@RestController
public class ProductQueryController {

    private final ProductQueryService productQueryService;

    @Autowired
    public ProductQueryController(ProductQueryService productQueryService) {
        this.productQueryService = productQueryService;
    }

    @GetMapping
    public ResponseEntity<List<ProductQueryDto>> getAllProducts(
            @RequestParam(value = "search", required = false) String search) {
        List<ProductQueryDto> products = productQueryService.getAllProducts(search);
        if (products.size() <= 0) {
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(null);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ProductQueryDto> getProduct(@PathVariable(value = "id") int id){
        ProductQueryDto product = productQueryService.getProduct(id);
        if(product == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        } else {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }
}
