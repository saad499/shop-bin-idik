package org.openlan2.shop_bin_idik.repository;

import org.openlan2.shop_bin_idik.entities.Support;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportRepository extends JpaRepository<Support, Long> {
    List<Support> findAllByOrderByDateCreatedDesc();
    
    Page<Support> findAllByOrderByDateCreatedDesc(Pageable pageable);
    
    List<Support> findByTypeReclamationContainingIgnoreCase(String typeReclamation);
}
