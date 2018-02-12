package com.yilin.www.spingboot.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.yilin.www.spingboot.domain.Product;
import com.yilin.www.spingboot.dto.ProductForm;

@Component
public class ProductToProductForm implements Converter<Product, ProductForm> {
    @Override
    public ProductForm convert(Product product) {
        ProductForm productForm = new ProductForm();
        productForm.setId(product.getId());
        productForm.setDescription(product.getDescription());
        productForm.setPrice(product.getPrice());
        productForm.setImageUrl(product.getImageUrl());
        return productForm;
    }
}