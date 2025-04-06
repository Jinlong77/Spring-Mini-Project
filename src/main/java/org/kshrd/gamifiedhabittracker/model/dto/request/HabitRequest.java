package org.kshrd.gamifiedhabittracker.model.dto.request;

import org.kshrd.gamifiedhabittracker.enumeration.FrequencyType;


public record HabitRequest (
         String title,
         String description,
         FrequencyType frequencyType,
         boolean isActive,
         String userId
) {
}
