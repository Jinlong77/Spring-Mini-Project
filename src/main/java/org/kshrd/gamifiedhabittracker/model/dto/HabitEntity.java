package org.kshrd.gamifiedhabittracker.model.dto;

import lombok.*;
import org.kshrd.gamifiedhabittracker.enumeration.FrequencyType;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitEntity {
    private UUID habitId;
    private String title;
    private String description;
    private FrequencyType frequencyType;
    private boolean isActive;
    // Add this to fix the error
    private AppUserEntity appUser;
}
