package org.openlan2.shop_bin_idik.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openlan2.shop_bin_idik.constant.StatusProduct;
import org.openlan2.shop_bin_idik.dto.SizeDto;

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
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Size> sizes;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Color> colors;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;
    
    private Integer stock;
    @Enumerated(EnumType.STRING)
    private StatusProduct status = StatusProduct.ACTIF;
    private LocalDateTime dateCreated = LocalDateTime.now();
    private Boolean isActiveProduct;
}
