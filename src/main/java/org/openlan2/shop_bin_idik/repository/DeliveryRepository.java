package org.openlan2.shop_bin_idik.repository;

import org.openlan2.shop_bin_idik.entities.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Page<Delivery> findByStatus(String status, Pageable pageable);
}
