package com.attornatus.gateways.repositories;

import com.attornatus.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
    List<Address> findByContactId(UUID contactId);
    List<Address> findByContactIdAndPrimaryAddressIsTrue(UUID contactId);
}
