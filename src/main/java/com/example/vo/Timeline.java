package com.example.vo;

import lombok.Data;

import java.util.Date;

@Data
public class Timeline {
    int amount;
    Date startDateTime;
    Date endDateTime;

    public boolean isOverlap(Timeline targetTimeline) {
            boolean isBefore = this.getEndDateTime().equals(targetTimeline.getStartDateTime()) || this.getEndDateTime().before(targetTimeline.getStartDateTime());
            boolean isAfter = this.getStartDateTime().equals(targetTimeline.getEndDateTime()) || this.getStartDateTime().after(targetTimeline.getEndDateTime());
            return !(isBefore || isAfter);
    }
}
