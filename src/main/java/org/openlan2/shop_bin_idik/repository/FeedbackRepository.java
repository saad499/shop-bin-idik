package org.openlan2.shop_bin_idik.repository;

import org.openlan2.shop_bin_idik.entities.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findAllByOrderByIdDesc();
    
    Page<Feedback> findAllByOrderByIdDesc(Pageable pageable);
    
    @Query("SELECT AVG(f.rating) FROM Feedback f")
    Double getAverageRating();
    
    List<Feedback> findByRating(Integer rating);
}
