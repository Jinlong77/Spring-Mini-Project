package org.kshrd.gamifiedhabittracker.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kshrd.gamifiedhabittracker.enumeration.FrequencyType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitDto {
    @NotBlank(message = "must not be blank")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;
    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;
    private FrequencyType frequency;
}
