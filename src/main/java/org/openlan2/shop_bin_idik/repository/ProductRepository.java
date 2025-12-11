package org.openlan2.shop_bin_idik.repository;

import org.openlan2.shop_bin_idik.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByIsActiveProduct(boolean isActive);
    Optional<Product> findFirstByIsActiveProduct(boolean isActive);
    @Query("SELECT p FROM Product p WHERE " +
           "LOWER(p.nom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(p.categorie.nom) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Product> searchByNomOrCategorie(@Param("searchTerm") String searchTerm);
    List<Product> findByNomContainingIgnoreCase(String nom);
    List<Product> findByCategorieNomContainingIgnoreCase(String categorieName);
}
