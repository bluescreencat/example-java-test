package com.example.booking.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@Data
@AllArgsConstructor
public class CalculateTableDTO {
    @NotEmpty
    @DateTimeFormat( pattern = "MM/dd/yyyy")
    private String bookingStartDate;

    @Size(min = 1)
    List<@Valid ReservedTableInfoDTO> reservedTableInfo;
}