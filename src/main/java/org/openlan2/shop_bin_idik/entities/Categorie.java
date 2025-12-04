package org.openlan2.shop_bin_idik.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "table_categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
}
