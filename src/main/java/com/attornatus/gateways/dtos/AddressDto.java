package com.attornatus.gateways.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AddressDto {
    @NotBlank
    private String street;
    @NotNull
    private int number;
    @NotBlank
    private String city;
    @NotBlank
    @Size(min = 2, max = 2)
    private String state;
    @NotNull
    @Min(value = 10000000)
    @Max(value = 99999999)
    private int cep;
    @NotNull
    private boolean primaryAddress;
}
