package org.openlan2.shop_bin_idik.repository;

import org.openlan2.shop_bin_idik.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
