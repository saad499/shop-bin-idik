package org.openlan2.shop_bin_idik.repository;
import org.openlan2.shop_bin_idik.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {
    List<Size> findByProductId(Long productId);
}
