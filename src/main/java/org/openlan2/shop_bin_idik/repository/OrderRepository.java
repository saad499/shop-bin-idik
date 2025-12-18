package org.openlan2.shop_bin_idik.repository;

import java.util.List;
import org.openlan2.shop_bin_idik.constant.StatusOrder;
import org.openlan2.shop_bin_idik.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByStatus(StatusOrder status, Pageable pageable);
    List<Order> findByStatus(String status);

    @Query("SELECT o FROM Order o WHERE o.status = 'EN_TRAITEMENT'")
    List<Order> findByStatusEnTraitement();
}
