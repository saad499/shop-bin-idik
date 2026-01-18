package org.openlan2.shop_bin_idik.repository;

import org.openlan2.shop_bin_idik.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    Optional<User> findByUsernameOrEmail(String username, String email);
}
