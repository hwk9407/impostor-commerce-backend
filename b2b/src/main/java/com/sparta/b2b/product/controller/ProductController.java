package com.sparta.b2b.product.controller;

import com.sparta.b2b.product.dto.request.ProductCreateRequest;
import com.sparta.b2b.product.dto.request.ProductUpdateRequest;
import com.sparta.b2b.product.dto.response.PageProductResponse;
import com.sparta.b2b.product.dto.response.ProductCreateResponse;
import com.sparta.b2b.product.dto.response.ProductSearchResponse;
import com.sparta.b2b.product.dto.response.ProductUpdateResponse;
import com.sparta.b2b.product.service.ProductService;
import com.sparta.common.annotation.CheckAuth;
import com.sparta.common.annotation.LoginMember;
import com.sparta.common.dto.MemberSession;
import com.sparta.common.enums.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 이것저것 추가
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;

	@CheckAuth(role = Role.B2B)
	@PostMapping()
	public ResponseEntity<ProductCreateResponse> createProduct(
		@RequestBody @Valid ProductCreateRequest request,
		@LoginMember(role = Role.B2B) MemberSession memberSession

	) {
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.body(productService.createProduct(memberSession.memberId(), request));
	}

	@CheckAuth(role = Role.B2B)
	@GetMapping()
	public ResponseEntity<PageProductResponse> totalSearchProduct(
		@RequestParam(defaultValue = "1") int page,
		@RequestParam(defaultValue = "10") int size,
		@RequestParam(defaultValue = "asc") String orderBy,
		@RequestParam(defaultValue = "name") String sortBy,
		@LoginMember(role = Role.B2B) MemberSession memberSession
	) {
		PageProductResponse response = productService.totalSearchProduct(memberSession.memberId(), page, size, orderBy, sortBy);

		return ResponseEntity
			.status(HttpStatus.OK)
			.body(response);
	}

	@CheckAuth(role = Role.B2B)
	@GetMapping("/{id}")
	public ResponseEntity<ProductSearchResponse> searchProduct(
		@PathVariable Long id,
		@LoginMember(role = Role.B2B) MemberSession memberSession
	) {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(productService.searchProduct(memberSession.memberId(), id));
	}

	@CheckAuth(role = Role.B2B)
	@PatchMapping("/{id}/stock-quantity")
	public ResponseEntity<ProductUpdateResponse> updateProduct(
		@PathVariable Long id,
		@LoginMember(role = Role.B2B) MemberSession memberSession,
		@RequestBody @Valid ProductUpdateRequest request
	) {
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(productService.updateProduct(memberSession.memberId(), id, request));
	}

	@CheckAuth(role = Role.B2B)
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(
		@PathVariable Long id,
		@LoginMember(role = Role.B2B) MemberSession memberSession
	) {
		productService.deleteProduct(memberSession.memberId(), id);
		return ResponseEntity
			.status(HttpStatus.NO_CONTENT).build();
	}
}
