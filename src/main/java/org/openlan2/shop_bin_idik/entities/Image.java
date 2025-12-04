package org.openlan2.shop_bin_idik.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "table_images")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private String imageUrl;
}
