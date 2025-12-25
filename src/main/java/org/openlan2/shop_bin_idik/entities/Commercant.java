package org.openlan2.shop_bin_idik.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "table_commercants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Commercant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String nom;
    private String prenom;
    private String telephone;
    private String nomCommerce;
    private String nameMagazin;
    private String categorie;
    private String adresse;
    private String ville;
    @Column(name = "logo_url", columnDefinition = "TEXT")
    private String logo;
    private String description;
    private String numImmatriculationFiscale;
    private String registreCommerce;
    private String documentAutre;
}
