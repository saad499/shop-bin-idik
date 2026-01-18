package org.openlan2.shop_bin_idik.repository;

import org.openlan2.shop_bin_idik.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUserId(Long userId);
    boolean existsByNom(String nom);
    boolean existsByPrenom(String prenom);
    boolean existsByNomAndPrenom(String nom, String prenom);
    boolean existsByTelephone(String telephone);
}
