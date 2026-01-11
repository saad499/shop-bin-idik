package org.openlan2.shop_bin_idik.service.impl;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.constant.StatusOrder;
import org.openlan2.shop_bin_idik.dto.CreateOrderRequest;
import org.openlan2.shop_bin_idik.dto.OrderDetailDto;
import org.openlan2.shop_bin_idik.entities.Order;
import org.openlan2.shop_bin_idik.entities.OrderItem;
import org.openlan2.shop_bin_idik.entities.Product;
import org.openlan2.shop_bin_idik.entities.Color;
import org.openlan2.shop_bin_idik.entities.Size;
import org.openlan2.shop_bin_idik.entities.Client;
import org.openlan2.shop_bin_idik.mappers.OrderDetailMapper;
import org.openlan2.shop_bin_idik.repository.ClientRepository;
import org.openlan2.shop_bin_idik.repository.ColorRepository;
import org.openlan2.shop_bin_idik.repository.OrderRepository;
import org.openlan2.shop_bin_idik.repository.ProductRepository;
import org.openlan2.shop_bin_idik.repository.SizeRepository;
import org.openlan2.shop_bin_idik.repository.OrderItemRepository;
import org.openlan2.shop_bin_idik.service.OrderService;
import org.openlan2.shop_bin_idik.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailMapper orderDetailMapper;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;

    @Override
    public Page<OrderDetailDto> getAllOrdersWithDetails(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        return orders.map(orderDetailMapper::mapToOrderDetailDto);
    }

    @Override
    public List<OrderDetailDto> getAllOrdersWithDetails() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderDetailMapper::mapToOrderDetailDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<OrderDetailDto> getOrdersByStatus(StatusOrder status, Pageable pageable) {
        Page<Order> orders = orderRepository.findByStatus(status, pageable);
        return orders.map(orderDetailMapper::mapToOrderDetailDto);
    }

    @Override
    @Transactional
    public OrderDetailDto progressOrderStatus(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        
        StatusOrder currentStatus = order.getStatus();
        StatusOrder nextStatus = getNextStatus(currentStatus);
        
        order.setStatus(nextStatus);
        Order updatedOrder = orderRepository.save(order);
        return orderDetailMapper.mapToOrderDetailDto(updatedOrder);
    }

    private StatusOrder getNextStatus(StatusOrder currentStatus) {
        switch (currentStatus) {
            case EN_TRAITEMENT:
                return StatusOrder.PREPAREE;
            case PREPAREE:
                return StatusOrder.EXPEDIEE;
            case EXPEDIEE:
                return StatusOrder.LIVREE;
            case LIVREE:
                return StatusOrder.LIVREE;
            default:
                throw new RuntimeException("Unknown status: " + currentStatus);
        }
    }

    @Override
    @Transactional
    public OrderDetailDto createOrder(CreateOrderRequest request) {
        // Get client
        Client client = clientRepository.findById(request.getClientId())
            .orElseThrow(() -> new RuntimeException("Client not found"));

        // Validate product availability before creating order
        for (var itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
            
            if (product.getDisponibilite() < itemRequest.getQuantity()) {
                throw new RuntimeException("Insufficient availability for product: " + product.getNom() + 
                    ". Available: " + product.getDisponibilite() + ", Requested: " + itemRequest.getQuantity());
            }
        }

        // Calculate total price
        Double totalPrice = request.getItems().stream()
            .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
            .sum();

        // Create order
        Order order = Order.builder()
            .client(client)
            .orderDate(LocalDateTime.now())
            .totalPrice(totalPrice)
            .status(StatusOrder.EN_TRAITEMENT)
            .shippingAddress(request.getShippingAddress())
            .billingAddress(request.getBillingAddress())
            .build();

        Order savedOrder = orderRepository.save(order);

        // Create order items and decrease product availability
        List<OrderItem> orderItems = request.getItems().stream()
            .map(itemRequest -> {
                Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
                
                Color color = colorRepository.findById(itemRequest.getColorId())
                    .orElseThrow(() -> new RuntimeException("Color not found"));
                
                Size size = sizeRepository.findById(itemRequest.getSizeId())
                    .orElseThrow(() -> new RuntimeException("Size not found"));

                // Decrease product availability
                productService.decreaseProductDisponibilite(product.getId(), itemRequest.getQuantity());

                return OrderItem.builder()
                    .order(savedOrder)
                    .product(product)
                    .color(color)
                    .size(size)
                    .quantite(itemRequest.getQuantity())
                    .prix(itemRequest.getUnitPrice())
                    .total(itemRequest.getUnitPrice() * itemRequest.getQuantity())
                    .build();
            })
            .collect(Collectors.toList());

        orderItemRepository.saveAll(orderItems);
        savedOrder.setOrderItems(orderItems);

        return orderDetailMapper.mapToOrderDetailDto(savedOrder);
    }
}

