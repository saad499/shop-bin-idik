package org.openlan2.shop_bin_idik.repository;

import org.openlan2.shop_bin_idik.entities.Commercant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommercantRepository extends JpaRepository<Commercant, Long> {
    List<Commercant> findByBusinessNameContainingIgnoreCase(String businessName);
    boolean existsByBusinessName(String businessName);
    boolean existsByTelephone(String telephone);
}
