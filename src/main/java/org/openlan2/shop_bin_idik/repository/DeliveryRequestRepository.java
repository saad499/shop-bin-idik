package org.openlan2.shop_bin_idik.repository;

import org.openlan2.shop_bin_idik.entities.DeliveryRequest;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRequestRepository extends JpaRepository<DeliveryRequest, Long> {
    Optional<DeliveryRequest> findByOrderIdAndDeliveryId(Long orderId, Long deliveryId);
}
