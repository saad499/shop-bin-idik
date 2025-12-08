package org.openlan2.shop_bin_idik.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openlan2.shop_bin_idik.constant.StatusProduct;

import java.time.LocalDateTime;

@Entity
@Table(name = "table_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commercant_id", nullable = false)
    private Commercant commercant;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    private String nom;
    private String description;
    private Double prix;
    @Enumerated(EnumType.STRING)
    private StatusProduct status = StatusProduct.ACTIF;
    private LocalDateTime dateCreated = LocalDateTime.now();
    private Boolean isActiveProduct;
}
