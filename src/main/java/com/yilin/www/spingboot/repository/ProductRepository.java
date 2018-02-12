package com.yilin.www.spingboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.yilin.www.spingboot.domain.Product;


@Service
public interface ProductRepository extends CrudRepository<Product, Long> {
}
