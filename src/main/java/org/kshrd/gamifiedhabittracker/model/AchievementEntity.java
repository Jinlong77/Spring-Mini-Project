package org.kshrd.gamifiedhabittracker.model;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AchievementEntity {

    private UUID achievementId;
    private String title;
    private String description;
    private String badge;
    private Integer xpRequired;
}