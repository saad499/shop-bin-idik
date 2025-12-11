package org.openlan2.shop_bin_idik.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "table_colors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private String colorName;
    private String colorCode; 
}
