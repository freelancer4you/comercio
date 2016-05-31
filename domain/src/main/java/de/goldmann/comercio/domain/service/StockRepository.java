package de.goldmann.comercio.domain.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import de.goldmann.comercio.domain.order.Stock;

//@RepositoryRestResource(collectionResourceRel = "stock", path = "stock")
public interface StockRepository extends JpaRepository<Stock, Long>
{
    Stock findByName(@Param("name") String name);

	Stock findBySearchKey(@Param("key") String key);
}
