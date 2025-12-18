package org.openlan2.shop_bin_idik.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openlan2.shop_bin_idik.constant.ContactMethod;
import org.openlan2.shop_bin_idik.constant.RequestStatus;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "delivery_requests")
public class DeliveryRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "delivery_id", nullable = false)
    private Delivery delivery;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(nullable = false)
    private String pickupAddress;

    @Column(nullable = false)
    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContactMethod contactMethod;

    @Column
    private Double estimatedPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status;

    @Column
    private String message;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;
}
