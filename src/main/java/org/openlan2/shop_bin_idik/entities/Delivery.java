package org.openlan2.shop_bin_idik.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "deliveries")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "livreur_id")
    private Livreur livreur;

    @Column(nullable = false)
    private Double note;

    @Column(nullable = false)
    private String delai;

    @Column(nullable = false)
    private String status;
}
