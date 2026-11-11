package com.ai.springaidemo.controllers;

import com.ai.springaidemo.dtos.AIRequest;
import com.ai.springaidemo.interfaces.IImage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import java.util.Map;

@RestController
public class GenAIImageController {
	
	@Qualifier("googleGenAIImage")
	private final IImage _imageService;


    public GenAIImageController(IImage imageService) {
        _imageService = imageService;
    }

    @GetMapping("/gen-image")
    public Map<String, Object> generate(
    	@Valid
    	@RequestBody AIRequest request) {
    	return _imageService.generate(request.getPrompt());
    }
}