package com.shy.sbank;

import com.shy.sbank.domain.product.entity.Product;
import com.shy.sbank.domain.product.entity.ProductType;
import com.shy.sbank.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testProductSave() {
        // given
        ProductType ptype = ProductType.SAVINGS;
        Product product = Product.builder()
                .productName("테스트 product입니다.")
                .productType(ptype)
                .interest(1.5f)
                .build();

        productRepository.save(product);
//        // when
        Product savedProduct = productRepository.getById(product.getId());

//        // then
        assertThat(product.getId()).isEqualTo(savedProduct.getId());
    }

}
