package de.goldmann.comercio.domain.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.goldmann.comercio.domain.order.Order;

//@RepositoryRestResource(collectionResourceRel = "order", path = "order")
public interface OrderRepository extends JpaRepository<Order, Long>
{
    List<Order> findByUserId(long userId);
}
