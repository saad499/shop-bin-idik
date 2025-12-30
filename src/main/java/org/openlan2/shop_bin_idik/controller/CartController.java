package org.openlan2.shop_bin_idik.controller;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.dto.AddToCartRequest;
import org.openlan2.shop_bin_idik.dto.CartItemDto;
import org.openlan2.shop_bin_idik.dto.CartSummaryDto;
import org.openlan2.shop_bin_idik.dto.CheckoutRequest;
import org.openlan2.shop_bin_idik.dto.OrderDetailDto;
import org.openlan2.shop_bin_idik.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartItemDto> addToCart(
            @RequestParam Long userId,
            @RequestBody AddToCartRequest request) {
        return ResponseEntity.ok(cartService.addToCart(userId, request));
    }

    @GetMapping("")
    public ResponseEntity<CartSummaryDto> getCart(@RequestParam Long userId) {
        return ResponseEntity.ok(cartService.getCartByUser(userId));
    }

    @PostMapping("/update")
    public ResponseEntity<CartItemDto> updateCartItemQuantity(
            @RequestParam Long cartItemId,
            @RequestParam Integer quantity) {
        CartItemDto updatedItem = cartService.updateCartItemQuantity(cartItemId, quantity);
        if (updatedItem == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeCartItem(@RequestParam Long cartItemId) {
        cartService.removeCartItem(cartItemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearCart(@RequestParam Long userId) {
        cartService.clearCart(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getCartItemCount(@RequestParam Long userId) {
        return ResponseEntity.ok(cartService.getCartItemCount(userId));
    }

    @PostMapping("/checkout")
    public ResponseEntity<OrderDetailDto> proceedToPayment(
            @RequestParam Long userId,
            @RequestBody CheckoutRequest checkoutRequest) {
        return ResponseEntity.ok(cartService.proceedToPayment(userId, checkoutRequest));
    }
}
