package com.attornatus.gateways.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ContactDto {
    @NotBlank
    private String name;
    @NotBlank
    private LocalDate dateOfBirth;
}
