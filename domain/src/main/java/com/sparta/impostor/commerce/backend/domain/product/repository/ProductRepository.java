package com.sparta.impostor.commerce.backend.domain.product.repository;

import com.sparta.impostor.commerce.backend.domain.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository <Product, Long>, ProductRepositoryQuery {
	Page<Product> findAllByMemberId(Long memberId, Pageable pageable);
}
