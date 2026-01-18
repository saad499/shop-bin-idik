package org.openlan2.shop_bin_idik.entities;

import org.openlan2.shop_bin_idik.constant.TypeVehicule;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "table_livreurs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Livreur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String nomComplet;
    private String prenomLivreur;
    private String telephone;
    private String nomCommerce;
    @Enumerated(EnumType.STRING)
    private TypeVehicule typeVehicule;
    private String numImmatriculation;
    private String photoConducteur;
    private String photoVehiculeRecto;
    private String photoVehiculeVerso;
    private String carteGriseRecto;
    private String carteGriseVerso;
    private String permisRecto;
    private String permisVerso;
    private String numeroPermis;

}
