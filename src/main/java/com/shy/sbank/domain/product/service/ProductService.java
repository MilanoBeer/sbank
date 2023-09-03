package com.shy.sbank.domain.product.service;

import com.shy.sbank.domain.product.dto.ProductRegisterRequestDto;
import com.shy.sbank.domain.product.entity.Product;
import com.shy.sbank.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public boolean register(ProductRegisterRequestDto dto) {
        Product product = Product.builder()
                .productName(dto.getProductName())
                .productType(dto.getProductType())
                .interest(dto.getInterest())
                .build();

        productRepository.save(product);

        return true;
    }
}
