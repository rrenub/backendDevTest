package rrenub.com.backenddevtest.myapp.myapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import rrenub.com.backenddevtest.myapp.myapp.dto.ProductDto;
import rrenub.com.backenddevtest.myapp.myapp.service.ProductsService;

import java.util.ArrayList;

@RestController
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/product/{id}/similar")
    public Flux<ProductDto> getSimilarProducts(@PathVariable String id) {
        System.out.println("getSimilarProducts called with id: " + id);
        return productsService.getSimilarProducts(id);
    }
}
