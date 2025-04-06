package org.kshrd.gamifiedhabittracker.model.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.kshrd.gamifiedhabittracker.enumeration.HabitStatus;
import org.kshrd.gamifiedhabittracker.model.dto.HabitEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
/*
    it will show an empty {} in the request body schema. Adding explicit Swagger annotations can resolve this.
    Solution :
    Add @Schema annotations to document the fields in your HabitLogRequest class:
 **/

import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitLogRequest {
    @Schema(example = "COMPLETED")
    private HabitStatus status;

    @Schema(example = "d6913c64-7dc5-4bd1-ab1a-1037fb25e4f9")
    private UUID habitId;

}