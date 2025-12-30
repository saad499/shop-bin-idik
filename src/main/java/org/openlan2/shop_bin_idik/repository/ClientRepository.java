package org.openlan2.shop_bin_idik.repository;

import org.openlan2.shop_bin_idik.entities.Client;
import org.openlan2.shop_bin_idik.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUser(User user);
}
