package com.example.backendtest.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{field}")
    private APIResponse<List<Product>> getProductWithSort(@PathVariable String field){
        List<Product> allProducts = productService.findProductsWithSorting(field);
        return new APIResponse<>(allProducts.size(), allProducts);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    private APIResponse<Page<Product>> getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Product> productsWithPagination = productService.findProductsWithPagination(offset, pageSize);
        return new APIResponse<>(productsWithPagination.getSize(), productsWithPagination);
    }

    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    private APIResponse<Page<Product>> getProductsWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
        Page<Product> productsWithPagination = productService.findProductsWithPaginationAndSorting(offset, pageSize, field);
        return new APIResponse<>(productsWithPagination.getSize(), productsWithPagination);
    }

    @PostMapping
    public void registerProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

    @DeleteMapping("delete/{productCode}")
    public void deleteProduct(@PathVariable("productCode") String productCode){
        productService.deleteProduct(productCode);
    }

    @PutMapping(path = "{productCode}")
    public void updateProduct(
            @PathVariable("productCode") String productCode,
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) String subcategory,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String retailPrice,
            @RequestParam(required = false) String status) {
        productService.update(productCode,productName,subcategory, brand, retailPrice, status);
    }
}
