package org.openlan2.shop_bin_idik.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openlan2.shop_bin_idik.constant.StatusDelivery;

import java.time.LocalDateTime;

@Entity
@Table(name = "table_delivery")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "livreur_id")
    private Livreur livreur;

    @Enumerated(EnumType.STRING)
    private StatusDelivery status = StatusDelivery.EN_ROUTE_COMMERCANT;
    private LocalDateTime dateAssignation = LocalDateTime.now();


}
