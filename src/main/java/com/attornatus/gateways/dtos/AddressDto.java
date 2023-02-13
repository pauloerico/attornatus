package com.attornatus.gateways.dtos;

import com.attornatus.entities.Contact;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
public class AddressDto {
    @NotBlank
    private String street;
    @NotBlank
    private int number;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    @NotBlank
    private int cep;
    @NotBlank
    private boolean isPrimaryAddress;
}
