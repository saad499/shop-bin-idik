package org.openlan2.shop_bin_idik.entities;

import jakarta.persistence.*;
import lombok.*;
import org.openlan2.shop_bin_idik.constant.StatusOrder;

import java.time.LocalDateTime;

@Entity
@Table(name = "table_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    private LocalDateTime dateCommande = LocalDateTime.now();
    private Double montantTotal;
    @Enumerated(EnumType.STRING)
    private StatusOrder status = StatusOrder.EN_TRAITEMENT;
}
