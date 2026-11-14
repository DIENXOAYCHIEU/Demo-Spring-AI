package com.ai.springaidemo.dtos;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TtsRequest {

    @NotBlank(message = "Nội dung văn bản không được để trống")
    private String text;

    @DecimalMin(value = "0.5", message = "Tốc độ đọc tối thiểu là 0.5")
    @DecimalMax(value = "2.0", message = "Tốc độ đọc tối đa là 2.0")
    private Double speed;
}
