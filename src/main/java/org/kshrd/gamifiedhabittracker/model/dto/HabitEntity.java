package org.kshrd.gamifiedhabittracker.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.kshrd.gamifiedhabittracker.enumeration.FrequencyType;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitEntity {

    private String habitId;
    private String title;
    private String description;
    private FrequencyType frequencyType;
    private boolean isActive;
    private String userId;


}
