package com.ai.springaidemo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.ai.audio.tts.TextToSpeechModel;
import org.springframework.ai.audio.tts.TextToSpeechOptions;
import org.springframework.ai.audio.tts.TextToSpeechPrompt;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AudioService {

    private final TextToSpeechModel textToSpeechModel;
    private final TranscriptionModel transcriptionModel;

    public byte[] textToSpeech(String text, Double speed) {
        if (speed == null) {
            return textToSpeechModel.call(text);
        }

        TextToSpeechOptions options = TextToSpeechOptions.builder()
                .speed(speed)
                .build();
        TextToSpeechPrompt prompt = new TextToSpeechPrompt(text, options);
        return textToSpeechModel.call(prompt).getResult().getOutput();
    }

    public String speechToText(MultipartFile audioFile) throws IOException {
        Resource audioResource = new ByteArrayResource(audioFile.getBytes()) {
            @Override
            public String getFilename() {
                return audioFile.getOriginalFilename();
            }
        };

        AudioTranscriptionPrompt prompt = new AudioTranscriptionPrompt(audioResource);
        AudioTranscriptionResponse response = transcriptionModel.call(prompt);
        return response.getResult().getOutput();
    }
}
