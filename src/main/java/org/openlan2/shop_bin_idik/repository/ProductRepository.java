package org.openlan2.shop_bin_idik.repository;

import org.openlan2.shop_bin_idik.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByIsActiveProduct(boolean isActive, Pageable pageable);
    Optional<Product> findFirstByIsActiveProduct(boolean isActive);
    @Query("SELECT p FROM Product p WHERE " +
           "LOWER(p.nom) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(p.categorie.nom) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    Page<Product> searchByNomOrCategorie(@Param("searchTerm") String searchTerm, Pageable pageable);
    Page<Product> findByNomContainingIgnoreCase(String nom, Pageable pageable);
    Page<Product> findByCategorieNomContainingIgnoreCase(String categorieName, Pageable pageable);
}
