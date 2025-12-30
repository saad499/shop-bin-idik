package org.openlan2.shop_bin_idik.repository;

import org.openlan2.shop_bin_idik.entities.CartItem;
import org.openlan2.shop_bin_idik.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserOrderByDateAddedDesc(User user);
    
    @Query("SELECT c FROM CartItem c WHERE c.user = :user AND c.product.id = :productId AND c.color.id = :colorId AND c.size.id = :sizeId")
    Optional<CartItem> findByUserAndProductAndColorAndSize(
        @Param("user") User user,
        @Param("productId") Long productId,
        @Param("colorId") Long colorId,
        @Param("sizeId") Long sizeId
    );
    
    void deleteByUser(User user);
    
    @Query("SELECT COUNT(c) FROM CartItem c WHERE c.user = :user")
    Integer countByUser(@Param("user") User user);
}
