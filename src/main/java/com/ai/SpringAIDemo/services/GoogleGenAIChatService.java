package com.ai.springaidemo.services;

import com.ai.springaidemo.interfaces.IChat;
import org.springframework.stereotype.Service;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;

@Service("googleGenAIChat")
public class GoogleGenAIChatService implements IChat{
	private final ChatModel _chatModel;

	public GoogleGenAIChatService(ChatModel chatModel){
		_chatModel=chatModel;
	}

	@Override
	public String getResponse(String prompt){
		ChatResponse response = _chatModel.call(
		    new Prompt(
		        prompt
		    ));
		String answer = response.getResult().getOutput().getText();
		var usage = response.getMetadata().getUsage();
		System.out.println();
		System.out.println("Token đầu vào: " + usage.getPromptTokens());
		System.out.println("Token đầu ra: " + usage.getCompletionTokens());
		System.out.println("Tổng token đã dùng: " + usage.getTotalTokens());
		return answer;
	}
}
