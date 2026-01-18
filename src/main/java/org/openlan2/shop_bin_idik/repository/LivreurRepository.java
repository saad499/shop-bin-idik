package org.openlan2.shop_bin_idik.repository;

import org.openlan2.shop_bin_idik.entities.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreurRepository extends JpaRepository<Livreur, Long> {
    boolean existsByNomComplet(String nomComplet);
    boolean existsByPrenomLivreur(String prenomLivreur);
    boolean existsByNomCompletAndPrenomLivreur(String nomComplet, String prenomLivreur);
    boolean existsByTelephone(String telephone);
    boolean existsByNumeroPermis(String numeroPermis);
}
