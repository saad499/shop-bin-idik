package org.openlan2.shop_bin_idik.service;

import org.openlan2.shop_bin_idik.dto.AddToCartRequest;
import org.openlan2.shop_bin_idik.dto.CartItemDto;
import org.openlan2.shop_bin_idik.dto.CartSummaryDto;
import org.openlan2.shop_bin_idik.dto.CheckoutRequest;
import org.openlan2.shop_bin_idik.dto.OrderDetailDto;

public interface CartService {
    CartItemDto addToCart(Long userId, AddToCartRequest request);
    CartSummaryDto getCartByUser(Long userId);
    CartItemDto updateCartItemQuantity(Long cartItemId, Integer quantity);
    void removeCartItem(Long cartItemId);
    void clearCart(Long userId);
    Integer getCartItemCount(Long userId);
    OrderDetailDto proceedToPayment(Long userId, CheckoutRequest checkoutRequest);
}
