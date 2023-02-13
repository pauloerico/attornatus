package com.attornatus.gateways.controllers;

import com.attornatus.entities.Contact;
import com.attornatus.gateways.dtos.ContactDto;
import com.attornatus.gateways.repositories.ContactRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts(){
        List<Contact> contacts = contactRepository.findAll();
        if (contacts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Contact> newContact(@RequestBody @Valid ContactDto contactDto){
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactDto, contact);
        contactRepository.save(contact);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable String id){
        Contact contact = contactRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Contact not found with id = " + id));
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> editContact(@PathVariable String id, @RequestBody @Valid ContactDto contactDto){
        Contact contact = contactRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Contact not found with id = " + id));
        BeanUtils.copyProperties(contactDto, contact);
        contactRepository.save(contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable String id){
        contactRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
