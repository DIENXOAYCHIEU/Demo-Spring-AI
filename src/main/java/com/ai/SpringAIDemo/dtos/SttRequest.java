package com.ai.springaidemo.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SttRequest {

    @NotNull(message = "File âm thanh không được để trống")
    private MultipartFile file;
    
    // private String language;
}
