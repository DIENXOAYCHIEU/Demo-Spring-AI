package com.ai.springaidemo.services;

import com.ai.springaidemo.interfaces.IImage;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("googleGenAIImage")
public class GoogleGenAIImageService implements IImage {

    private final ImageModel _imageModel;

    public GoogleGenAIImageService(ImageModel imageModel) {
        _imageModel = imageModel;
    }

    @Override
    public Map<String, Object> generate(String prompt) {
        ImagePrompt imagePrompt = new ImagePrompt(prompt);
        ImageResponse imageResponse = _imageModel.call(imagePrompt);
        return Map.of("images", imageResponse);
    }
}
