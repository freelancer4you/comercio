package de.goldmann.comercio.domain.service;

import org.springframework.data.jpa.repository.JpaRepository;

import de.goldmann.comercio.domain.User;

//@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends JpaRepository<User, Long>
{
    // User findByLastName(@Param("name") String name);
}
