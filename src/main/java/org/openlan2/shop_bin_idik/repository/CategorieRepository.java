package org.openlan2.shop_bin_idik.repository;

import org.openlan2.shop_bin_idik.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    List<Categorie> findAllByIsActiveCategory(boolean isActiveCategory);
    Optional<Categorie> findFirstByIsActiveCategory(boolean isActiveCategory);
}
