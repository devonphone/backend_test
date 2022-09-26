package com.example.backendtest.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(){
       return productRepository.findAll();
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public Product findOne(String productCode){
        return productRepository.findById(productCode).get();
    }

    public void deleteProduct(String productCode){
        boolean exists = productRepository.existsById(productCode);
        if(!exists){
            throw new IllegalStateException("Product with code " + productCode + " does not exist");
        }
        productRepository.deleteById(productCode);
    }


    @Transactional
    public void update(String productCode, String productName, String subCategory, String brand, String retailPrice, String status) {
        Product product = productRepository.findById(productCode)
                .orElseThrow(() -> new IllegalStateException(
                        "Product with code " + productCode + " not exist"));

        if(productName != null && productName.length() > 0 && !Objects.equals(product.getProductName(), productName)){
            product.setProductName(productName);
        }
        if(subCategory != null && subCategory.length() > 0 && !Objects.equals(product.getSubCategory(), subCategory)){
            product.setSubCategory(subCategory);
        }

        if(brand != null && brand.length() > 0 && !Objects.equals(product.getBrand(), brand)){
            product.setBrand(brand);
        }

        if(retailPrice != null && retailPrice.length() > 0 && !Objects.equals(product.getRetailPrice(), retailPrice)){
            product.setRetailPrice(retailPrice);
        }

        if(status != null && status.length() > 0 && !Objects.equals(product.getStatus(), status)){
            product.setStatus(status);
        }
    }

    public Optional<Product> findByProductName(String productName){
        return productRepository.findProductByProductName(productName);
    }

    public List<Product> findProductsWithSorting(String field){
        return  productRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public Page<Product> findProductsWithPagination(int offset, int pageSize){
        Page<Product> products = productRepository.findAll(PageRequest.of(offset,pageSize));
        return products;
    }

    public Page<Product> findProductsWithPaginationAndSorting(int offset, int pageSize, String field){
        Page<Product> products = productRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
        return products;
    }
}
