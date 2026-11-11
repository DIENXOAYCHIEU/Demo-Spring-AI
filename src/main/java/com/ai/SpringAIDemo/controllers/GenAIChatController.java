package com.ai.springaidemo.controllers;

import com.ai.springaidemo.dtos.AIRequest;
import com.ai.springaidemo.dtos.CreateRecipeRequest;
import com.ai.springaidemo.interfaces.IChat;
import com.ai.springaidemo.interfaces.ICreate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Qualifier;
import jakarta.validation.Valid;

@RestController
public class GenAIChatController {

	@Qualifier("googleGenAIChat")
	private final IChat _chatService;

	@Qualifier("recipeCreateService")
	private final ICreate<CreateRecipeRequest> _recipeCreateService;

	public GenAIChatController(
		IChat chatService,
		ICreate<CreateRecipeRequest> recipeCreateService
	){
		_recipeCreateService=recipeCreateService;
		_chatService=chatService;
	}

	@GetMapping("ask-ai")
	public String getResponse(
		@Valid
		@RequestBody AIRequest request
	){
		return _chatService.getResponse(request.getPrompt());
	}

	@GetMapping("recipe-creator")
	public String getRecipe(
		@Valid
		@RequestBody CreateRecipeRequest request
	){
		return _recipeCreateService.create(request);
	}
}
