package com.example.booking.services;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.booking.dto.CalculateTableDTO;
import com.example.booking.dto.ReservedTableInfoDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BookingServiceTest {
    @Autowired
    private BookingService bookingService;

    final String defaultBookingStartDate = "02/04/2024";
    final String defaultBookerName = "booker name";
    final String defaultTel = "8888888888";

    @Test
    void contextLoads() throws Exception {
        assertThat(bookingService).isNotNull();
    }

    @Test
    void whenBookingNotOverlapAndHaveManyCustomerShouldReturnValidAmountOfTable() {
        CalculateTableDTO booking = new CalculateTableDTO(defaultBookingStartDate, new ArrayList<ReservedTableInfoDTO>(){{
            add(new ReservedTableInfoDTO(defaultBookerName, defaultTel, "09:30", "10:30", 80));
        }});
        int tableAmount = bookingService.getMinimumTableAmount(booking);
        assertThat(tableAmount).isEqualTo(20);

        CalculateTableDTO booking2 = new CalculateTableDTO(defaultBookingStartDate, new ArrayList<ReservedTableInfoDTO>(){{
            add(new ReservedTableInfoDTO(defaultBookerName, defaultTel, "09:30", "10:30", 30));
        }});
        int tableAmount2 = bookingService.getMinimumTableAmount(booking2);
        assertThat(tableAmount2).isEqualTo(8);
    }

    @Test
    void whenBookingHaveManyOverlapAndAmountOfCustomerEqualOrLessThanCapacityOfOneTableShouldReturnValidMaximumAmountOfTable() {
        CalculateTableDTO booking = new CalculateTableDTO(defaultBookingStartDate, new ArrayList<ReservedTableInfoDTO>(){{
            add(new ReservedTableInfoDTO(defaultBookerName, defaultTel, "09:30", "10:30", 4));
            add(new ReservedTableInfoDTO(defaultBookerName, defaultTel, "10:20", "11:20", 4));
            add(new ReservedTableInfoDTO(defaultBookerName, defaultTel, "08:40", "09:40", 3));
            add(new ReservedTableInfoDTO(defaultBookerName, defaultTel, "09:40", "10:20", 2));
            add(new ReservedTableInfoDTO(defaultBookerName, defaultTel, "09:30", "10:30", 1));
            add(new ReservedTableInfoDTO(defaultBookerName, defaultTel, "09:20", "10:40", 1));
        }});
        int tableAmount = bookingService.getMinimumTableAmount(booking);
        assertThat(tableAmount).isEqualTo(6);
    }

    @Test
    void whenBookingHaveTwoOverlapGroupAndLargeAmountOfCustomerShouldReturnValidMaximumAmountOfTable() {
        CalculateTableDTO booking = new CalculateTableDTO(defaultBookingStartDate, new ArrayList<ReservedTableInfoDTO>(){{
                // overlap group 1 (53 table)
            add(new ReservedTableInfoDTO(defaultBookerName, defaultTel, "09:30", "10:30", 60));
            add(new ReservedTableInfoDTO(defaultBookerName, defaultTel, "10:20", "11:20", 70));
            add(new ReservedTableInfoDTO(defaultBookerName, defaultTel, "08:40", "09:40", 80));
                // overlap group 2 (76 table)
            add(new ReservedTableInfoDTO(defaultBookerName, defaultTel, "13:40", "14:40", 90));
            add(new ReservedTableInfoDTO(defaultBookerName, defaultTel, "14:30", "15:30", 100));
            add(new ReservedTableInfoDTO(defaultBookerName, defaultTel, "13:20", "14:20", 110));
        }});
        int tableAmount = bookingService.getMinimumTableAmount(booking);
        assertThat(tableAmount).isEqualTo(76);
    }
}
