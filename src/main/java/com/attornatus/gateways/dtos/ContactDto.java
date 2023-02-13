package com.attornatus.gateways.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContactDto {
    @NotBlank
    private String name;
    @NotNull
    private LocalDate dateOfBirth;
}
