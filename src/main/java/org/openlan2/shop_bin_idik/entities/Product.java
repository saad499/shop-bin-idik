package org.openlan2.shop_bin_idik.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openlan2.shop_bin_idik.constant.StatusProduct;
import org.openlan2.shop_bin_idik.dto.SizeDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorie_id")
    @JsonIgnoreProperties({"products"})
    private Categorie categorie;

    private String nom;
    private String description;
    private Double prix;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("product-sizes")
    private List<Size> sizes = new ArrayList<>();
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("product-colors")
    private List<Color> colors = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("product-images")
    private List<Image> images = new ArrayList<>();
    
    private Integer stock;
    private Integer disponibilite; // Available quantity
    @Enumerated(EnumType.STRING)
    private StatusProduct status = StatusProduct.ACTIF;
    private LocalDateTime dateCreated = LocalDateTime.now();
    private Boolean isActiveProduct;
}
