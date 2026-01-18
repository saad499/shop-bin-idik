package org.openlan2.shop_bin_idik.service.impl;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.constant.StatusOrder;
import org.openlan2.shop_bin_idik.dto.*;
import org.openlan2.shop_bin_idik.entities.*;
import org.openlan2.shop_bin_idik.repository.*;
import org.openlan2.shop_bin_idik.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ClientRepository clientRepository;

    @Override
    public CartItemDto addToCart(Long userId, AddToCartRequest request) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        Product product = productRepository.findById(request.getProductId())
            .orElseThrow(() -> new RuntimeException("Product not found"));
        
        Color color = colorRepository.findById(request.getColorId())
            .orElseThrow(() -> new RuntimeException("Color not found"));
        
        Size size = sizeRepository.findById(request.getSizeId())
            .orElseThrow(() -> new RuntimeException("Size not found"));

        // Check if item already exists in cart
        var existingItem = cartItemRepository.findByUserAndProductAndColorAndSize(
            user, request.getProductId(), request.getColorId(), request.getSizeId());

        CartItem cartItem;
        if (!existingItem.isPresent()) {
            // Create new cart item
            cartItem = CartItem.builder()
                .user(user)
                .product(product)
                .color(color)
                .size(size)
                .quantity(request.getQuantity())
                .unitPrice(product.getPrix())
                .build();
            cartItem = cartItemRepository.save(cartItem);
        } else {
            cartItem = existingItem.get();
        }

        return mapToCartItemDto(cartItem);
    }

    @Override
    public CartSummaryDto getCartByUser(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        List<CartItem> cartItems = cartItemRepository.findByUserOrderByDateAddedDesc(user);
        List<CartItemDto> cartItemDtos = cartItems.stream()
            .map(this::mapToCartItemDto)
            .collect(Collectors.toList());

        Double totalPrice = cartItemDtos.stream()
            .mapToDouble(CartItemDto::getTotalPrice)
            .sum();

        Integer totalItems = cartItemDtos.stream()
            .mapToInt(CartItemDto::getQuantity)
            .sum();

        return CartSummaryDto.builder()
            .items(cartItemDtos)
            .totalItems(totalItems)
            .totalPrice(totalPrice)
            .build();
    }

    @Override
    public CartItemDto updateCartItemQuantity(Long cartItemId, Integer quantity) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
            .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (quantity <= 0) {
            cartItemRepository.delete(cartItem);
            return null;
        }

        cartItem.setQuantity(quantity);
        cartItem = cartItemRepository.save(cartItem);
        return mapToCartItemDto(cartItem);
    }

    @Override
    public void removeCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public void clearCart(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        cartItemRepository.deleteByUser(user);
    }

    @Override
    public Integer getCartItemCount(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return cartItemRepository.countByUser(user);
    }

    @Override
    public OrderDetailDto proceedToPayment(Long userId, CheckoutRequest checkoutRequest) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Get the client associated with this user
        Client client = clientRepository.findByUserId(userId)
            .orElseThrow(() -> new RuntimeException("Client not found for this user"));

        // Get cart items
        List<CartItem> cartItems = cartItemRepository.findByUserOrderByDateAddedDesc(user);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // Calculate total price
        Double totalPrice = cartItems.stream()
            .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
            .sum();

        // Create order
        Order order = Order.builder()
            .client(client)
            .orderDate(LocalDateTime.now())
            .totalPrice(totalPrice)
            .status(StatusOrder.EN_TRAITEMENT)
            .shippingAddress(checkoutRequest.getShippingAddress())
            .billingAddress(checkoutRequest.getBillingAddress())
            .build();

        Order savedOrder = orderRepository.save(order);

        // Create order items from cart items
        List<OrderItem> orderItems = cartItems.stream()
            .map(cartItem -> this.mapCartItemToOrderItem(cartItem, savedOrder))
            .collect(Collectors.toList());

        orderItemRepository.saveAll(orderItems);

        // Clear cart after successful order creation
        cartItemRepository.deleteByUser(user);

        // Return order details
        return mapToOrderDetailDto(savedOrder, orderItems);
    }

    private OrderItem mapCartItemToOrderItem(CartItem cartItem, Order order) {
        return OrderItem.builder()
            .order(order)
            .product(cartItem.getProduct())
            .color(cartItem.getColor())
            .size(cartItem.getSize())
            .quantite(cartItem.getQuantity())
            .prix(cartItem.getUnitPrice())
            .total(cartItem.getUnitPrice() * cartItem.getQuantity())
            .build();
    }

    private CartItemDto mapToCartItemDto(CartItem cartItem) {
        String productImage = null;
        if (cartItem.getProduct().getImages() != null && !cartItem.getProduct().getImages().isEmpty()) {
            productImage = cartItem.getProduct().getImages().get(0).getImageUrl();
        }

        return CartItemDto.builder()
            .id(cartItem.getId())
            .productName(cartItem.getProduct().getNom())
            .productImage(productImage)
            .colorName(cartItem.getColor().getColorName())
            .sizeName(cartItem.getSize().getSizeName())
            .quantity(cartItem.getQuantity())
            .unitPrice(cartItem.getUnitPrice())
            .totalPrice(cartItem.getUnitPrice() * cartItem.getQuantity())
            .build();
    }

    private OrderDetailDto mapToOrderDetailDto(Order order, List<OrderItem> orderItems) {
        List<OrderItemDetailDto> orderItemDtos = orderItems.stream()
            .map(this::mapToOrderItemDetailDto)
            .collect(Collectors.toList());

        Client client = order.getClient();
        User user = client.getUser();

        return OrderDetailDto.builder()
            .orderStatus(order.getStatus())
            .clientNom(client.getNom())
            .clientPrenom(client.getPrenom())
            .clientEmail(user.getEmail())
            .clientTelephone(client.getTelephone())
            .clientAdresse(order.getShippingAddress())
            .items(orderItemDtos)
            .build();
    }

    private OrderItemDetailDto mapToOrderItemDetailDto(OrderItem orderItem) {
        String productImage = null;
        if (orderItem.getProduct().getImages() != null && !orderItem.getProduct().getImages().isEmpty()) {
            productImage = orderItem.getProduct().getImages().get(0).getImageUrl();
        }

        return OrderItemDetailDto.builder()
            .productName(orderItem.getProduct().getNom())
            .productImage(productImage)
            .colorName(orderItem.getColor().getColorName())
            .sizeName(orderItem.getSize().getSizeName())
            .quantity(orderItem.getQuantite())
            .unitPrice(orderItem.getPrix())
            .totalPrice(orderItem.getTotal())
            .build();
    }
}
