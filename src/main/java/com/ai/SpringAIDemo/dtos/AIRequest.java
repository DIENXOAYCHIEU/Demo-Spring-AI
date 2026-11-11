package com.ai.springaidemo.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AIRequest {
	
	@NotBlank(message="Câu prompt không được để trống")
    private String prompt;
}
