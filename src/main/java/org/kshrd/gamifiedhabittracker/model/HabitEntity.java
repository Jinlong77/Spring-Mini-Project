package org.kshrd.gamifiedhabittracker.model;

import lombok.*;
import org.kshrd.gamifiedhabittracker.enumeration.FrequencyType;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitEntity {

    private UUID habitId;
    private String title;
    private String description;
    private FrequencyType frequency;
    private boolean isActive;
    private AppUserEntity user;
    private Instant createdAt;
}
