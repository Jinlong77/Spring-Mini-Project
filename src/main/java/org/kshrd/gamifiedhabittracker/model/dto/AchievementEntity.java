package org.kshrd.gamifiedhabittracker.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AchievementEntity {

    private String achievementId;
    private String title;
    private String description;
    private String badge;
    private Integer xpRequired;
}