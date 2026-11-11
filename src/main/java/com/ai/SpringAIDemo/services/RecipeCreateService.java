package com.ai.springaidemo.services;

import com.ai.springaidemo.dtos.CreateRecipeRequest;
import com.ai.springaidemo.interfaces.ICreate;
import org.springframework.stereotype.Service;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import java.util.Map;

@Service("recipeCreateService")
public class RecipeCreateService implements ICreate<CreateRecipeRequest>{
	private final ChatModel _chatModel;

	public RecipeCreateService(ChatModel chatModel){
		_chatModel=chatModel;
	}

	@Override
	public String create(CreateRecipeRequest request){
		var template = """
			Tôi muốn tạo một công thức được sử dụng dựa theo nguyên liệu: {ingredients}.
			Ẩm thực tôi thích là {cuisine}.
			Vui lòng xem xét dựa theo hạn chế ăn kiêng hạn chế ăn uống: {dietaryRestrictions}.
			Vui lòng cung cấp tôi với công thức chi tiết bao gồm tiêu đề, danh sách của nguyên liệu và hướng dẫn nấu ăn.
		""";

		PromptTemplate promptTemplate = new PromptTemplate(template);
		Map<String, Object> params = Map.of(
			"ingredients", request.getIngredients(),
			"cuisine", request.getCuisine(),
			"dietaryRestrictions", request.getDietaryRestrictions()
		);

		Prompt prompt = promptTemplate.create(params);
		ChatResponse response = _chatModel.call(prompt);

		String answer = response.getResult().getOutput().getText();
		var usage = response.getMetadata().getUsage();
		System.out.println();
		System.out.println("Token đầu vào: " + usage.getPromptTokens());
		System.out.println("Token đầu ra: " + usage.getCompletionTokens());
		System.out.println("Tổng token đã dùng: " + usage.getTotalTokens());
		return answer;

	}
}
