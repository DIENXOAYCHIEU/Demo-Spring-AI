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
public class CreateRecipeRequest {
	
	@NotBlank(message="Nguyên liệu không được để trống")
    private String ingredients;

    @NotBlank(message="Ẩm thực không được để trống")
    private String cuisine;

    @NotBlank(message="Hạn chế ăn kiêng không được để trống")
    private String dietaryRestrictions;

}
