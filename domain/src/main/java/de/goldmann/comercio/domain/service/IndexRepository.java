package de.goldmann.comercio.domain.service;

import org.springframework.data.jpa.repository.JpaRepository;

import de.goldmann.comercio.domain.order.LeadingIndex;

//@RepositoryRestResource(collectionResourceRel = "order", path = "order")
public interface IndexRepository extends JpaRepository<LeadingIndex, Long>
{
	LeadingIndex findByName(String string);

	LeadingIndex findBySearchKey(String key);
}
