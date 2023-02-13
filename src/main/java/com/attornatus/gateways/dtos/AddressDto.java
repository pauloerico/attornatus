package com.attornatus.gateways.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressDto {
    @NotBlank
    private String street;
    @NotBlank
    private int number;
    @NotBlank
    private String city;
    @NotBlank
    @Size(min = 2, max = 2)
    private String state;
    @NotBlank
    @Size(min = 2, max = 2)
    private int cep;
    @NotBlank
    private boolean primaryAddress;
}
