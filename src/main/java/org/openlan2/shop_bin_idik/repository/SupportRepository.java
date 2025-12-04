package org.openlan2.shop_bin_idik.repository;

import org.openlan2.shop_bin_idik.entities.Support;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportRepository extends JpaRepository<Support, Long> {
}
