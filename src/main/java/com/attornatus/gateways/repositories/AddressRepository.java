package com.attornatus.gateways;

import com.attornatus.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, String> {
    List<Address> findByContactId(String contactId);
    List<Address> findByContactIdAndPrimaryAddress(String contactId);
}
