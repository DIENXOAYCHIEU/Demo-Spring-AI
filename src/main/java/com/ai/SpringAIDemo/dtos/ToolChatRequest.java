package com.ai.springaidemo.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToolChatRequest {

    @NotBlank(message = "Prompt không được để trống")
    private String prompt;
}
