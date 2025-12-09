package org.openlan2.shop_bin_idik.repository;

import org.openlan2.shop_bin_idik.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByIsActiveProduct(boolean isActive);
    Optional<Product> findFirstByIsActiveProduct(boolean isActive);
}
