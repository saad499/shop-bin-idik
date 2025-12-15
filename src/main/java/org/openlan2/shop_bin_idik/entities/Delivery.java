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

    @Column(nullable = false)
    private String nomComplet;

    @Column(nullable = false)
    private Double note; // Rating (e.g., 1-5 stars)

    @Column(nullable = false)
    private String typeVehicule;

    @Column(nullable = false)
    private String delai; // Delivery time (e.g., "30 min", "1 hour")

    @Column(nullable = false)
    private String status; // e.g., "DISPONIBLE", "EN_LIVRAISON", "INDISPONIBLE"
}
