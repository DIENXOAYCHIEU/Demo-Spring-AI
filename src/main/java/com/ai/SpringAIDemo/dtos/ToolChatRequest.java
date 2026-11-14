package com.ai.springaidemo.dtos;

import jakarta.validation.constraints.NotBlank;

public record ToolChatRequest(
        @NotBlank(message = "Prompt không được để trống")
        String prompt
) {}
