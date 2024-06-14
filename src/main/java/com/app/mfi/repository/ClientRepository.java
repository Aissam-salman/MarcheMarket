package com.app.mfi.repository;

import com.app.mfi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findOneByFirstname(@Param("firstname") String firstname);
}
