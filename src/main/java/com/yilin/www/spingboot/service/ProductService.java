package com.yilin.www.spingboot.service;

import java.util.List;

import com.yilin.www.spingboot.domain.Product;
import com.yilin.www.spingboot.dto.ProductForm;

public interface ProductService {

    List<Product> listAll();

    Product getById(Long id);

    Product saveOrUpdate(Product product);

    void delete(Long id);

    Product saveOrUpdateProductForm(ProductForm productForm);
}