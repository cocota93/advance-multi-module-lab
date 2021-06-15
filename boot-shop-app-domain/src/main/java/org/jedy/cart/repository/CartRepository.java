package org.jedy.cart.repository;

import org.jedy.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long>, CartRepositoryCustom {



}