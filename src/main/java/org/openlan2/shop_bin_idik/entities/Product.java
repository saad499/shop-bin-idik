package org.openlan2.shop_bin_idik.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openlan2.shop_bin_idik.constant.StatusProduct;

import java.time.LocalDateTime;
import java.util.List;

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

    /*@ManyToOne
    @JoinColumn(name = "commercant_id", nullable = false)
    private Commercant commercant;*/

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    private String nom;
    private String description;
    private Double prix;
    
    @ElementCollection
    @CollectionTable(name = "product_sizes", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "size")
    private List<String> sizes;
    
    private Integer stock;
    @Enumerated(EnumType.STRING)
    private StatusProduct status = StatusProduct.ACTIF;
    private LocalDateTime dateCreated = LocalDateTime.now();
    private Boolean isActiveProduct;
}
