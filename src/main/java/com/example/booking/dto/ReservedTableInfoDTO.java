package com.example.booking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
public class ReservedTableInfoDTO {
    @NotEmpty
    @NotBlank
    @Size(min = 1, max = 255, message = "Please enter the booker name")
    private String bookerName;

    @NotEmpty
    @Pattern(regexp="(^$|[0-9]{10})", message = "The telephone number is invalid format")
    private String tel;

    @NotEmpty
    @DateTimeFormat( pattern = "HH:mm")
    private String bookingStartTime;

    @NotEmpty
    @DateTimeFormat( pattern = "HH:mm")
    private String bookingEndTime;

    @Range(min = 1, max = 100)
    private Integer customerAmount;
}