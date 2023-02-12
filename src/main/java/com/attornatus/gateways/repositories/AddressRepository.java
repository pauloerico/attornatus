package com.attornatus.gateways.repositories;

import com.attornatus.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
    List<Address> findByContactId(String contactId);
    List<Address> findByContactIdAndPrimaryAddress(String contactId);
}
