package com.example.booking.services;

import com.example.booking.dto.CalculateTableDTO;
import com.example.booking.dto.ReservedTableInfoDTO;
import com.example.vo.Timeline;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    final double CUSTOMER_AMOUNT_PER_TABLE = 4.0;
    public int getMaximumTableAmount(CalculateTableDTO calculateTableDTO) {
        List<Timeline> timelines = getTimelines(calculateTableDTO);
        int maxTable = getMaximumTableAmount(timelines);
        return maxTable;
    }

    private List<Timeline> getTimelines(CalculateTableDTO calculateTableDTO) {
        List<Timeline> timelines = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        List<ReservedTableInfoDTO> reservedTableInfo = calculateTableDTO.getReservedTableInfo();
        for (int i = 0; i < reservedTableInfo.size(); i++) {
            String startBookingDateTime = calculateTableDTO.getBookingStartDate() + " " + reservedTableInfo.get(i).getBookingStartTime();
            String endBookingDateTime = calculateTableDTO.getBookingStartDate() + " " + reservedTableInfo.get(i).getBookingEndTime();
            try {
                Date startDateTime = formatter.parse(startBookingDateTime);
                Date endDateTime = formatter.parse(endBookingDateTime);
                Timeline timeline = new Timeline();
                timeline.setStartDateTime(startDateTime);
                timeline.setEndDateTime(endDateTime);
                double tableAmount = Math.ceil(Double.valueOf(reservedTableInfo.get(i).getCustomerAmount()) / CUSTOMER_AMOUNT_PER_TABLE);
                timeline.setAmount((int)tableAmount);
                timelines.add(timeline);
            } catch (Exception e) {
                // not implement
            }
        }
        return timelines;
    }

    private int getMaximumTableAmount(List<Timeline> timelines) {
        int maxTableAmount = 0;
        for (int i = 0; i < timelines.size(); i++) {
            Timeline currentTimeline = timelines.get(i);
            int tableAmount = currentTimeline.getAmount();
            for (int j = 0; j <timelines.size(); j++) {
                if (i == j) {
                    continue;
                }
                Timeline targetTimeline = timelines.get(j);
                if (currentTimeline.isOverlap(targetTimeline)) {
                    tableAmount += targetTimeline.getAmount();
                }
            }
            maxTableAmount = Math.max(maxTableAmount, tableAmount);
        }
        return maxTableAmount;
    }
}
