package com.example.letscode.Domain.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPriceRequestDto {
    String item;
    @NotNull
    String addPriceValue;
}
