package com.attornatus.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.UUID;

@Entity
@Data
@Table(name = "TB_ADDRESSES")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String street;
    private int number;
    private String city;
    @Column(length = 2)
    private String state;
    @Column(length = 8)
    private int CEP;
    private boolean isPrimaryAddress;
    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Contact contact;
}
