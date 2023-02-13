package com.attornatus.gateways.controllers;

import com.attornatus.entities.Address;
import com.attornatus.entities.Contact;
import com.attornatus.gateways.dtos.AddressDto;
import com.attornatus.gateways.repositories.AddressRepository;
import com.attornatus.gateways.repositories.ContactRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class AddressController {

    private final ContactRepository contactRepository;
    private final AddressRepository addressRepository;

    public AddressController(ContactRepository contactRepository, AddressRepository addressRepository) {
        this.contactRepository = contactRepository;
        this.addressRepository = addressRepository;
    }

    @GetMapping("/contacts/{contactId}/addresses")
    public ResponseEntity<List<Address>> getAllContactAddresses(@PathVariable UUID contactId){
        if (!contactRepository.existsById(contactId)){
            throw new EntityNotFoundException("Contact not found with id = " + contactId);
        }
        List<Address> addresses = addressRepository.findByContactId(contactId);
        if (addresses.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @GetMapping("/contacts/{contactId}/primary-address")
    public ResponseEntity<List<Address>> getContactPrimaryAddress(@PathVariable UUID contactId){
        if (!contactRepository.existsById(contactId)){
            throw new EntityNotFoundException("Contact not found with id = " + contactId);
        }
        List<Address> primaryAddresses = addressRepository.findByContactIdAndPrimaryAddress(contactId, true);
        if (primaryAddresses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(primaryAddresses, HttpStatus.OK);
    }

    @PostMapping("/contacts/{contactId}/addresses")
    public ResponseEntity<Address> newAddress(@PathVariable UUID contactId, @RequestBody @Valid AddressDto addressDto){
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(()->new EntityNotFoundException("Contact not found with id = " + contactId));
        Address address = new Address();
        BeanUtils.copyProperties(addressDto, address);
        address.setContact(contact);
        addressRepository.save(address);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @PutMapping("/addresses/{id}")
    public ResponseEntity<Address> editAddress(@PathVariable UUID id, @RequestBody @Valid AddressDto addressDto){
        Address address = addressRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Address not found with id = " + id));
        BeanUtils.copyProperties(addressDto, address);
        addressRepository.save(address);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @DeleteMapping("/addresses/{id}")
    public ResponseEntity<HttpStatus> deleteAddress(@PathVariable UUID id){
        addressRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
