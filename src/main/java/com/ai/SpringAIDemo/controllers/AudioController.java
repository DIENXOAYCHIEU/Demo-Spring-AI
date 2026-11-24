package com.ai.springaidemo.services;

import com.ai.springaidemo.dtos.TtsRequest;
import com.ai.springaidemo.dtos.SttRequest;
import com.ai.springaidemo.services.AudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;

import java.io.IOException;

@RestController
@RequestMapping("/api/audio")
@RequiredArgsConstructor
public class AudioController {

    private final AudioService audioService;

	@PostMapping("/tts")
	public ResponseEntity<byte[]> generateAudio(
		@Valid
		@RequestBody TtsRequest request
	) {
	    byte[] wavBytes = audioService.textToSpeech(
	    	request.getText(),
	    	request.getSpeed()
	    );

	    return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"output.wav\"")
	            .contentType(MediaType.parseMediaType("audio/wav"))
	            .body(wavBytes);
	}

    @PostMapping(value = "/stt", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> transcribeAudio(
		@Valid
		@ModelAttribute SttRequest request
	) throws IOException {
	    String transcript = audioService.speechToText(request.getFile());
	    return ResponseEntity.ok(transcript);
	}

}
