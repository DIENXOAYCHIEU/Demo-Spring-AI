package com.ai.springaidemo.controllers;

import com.ai.springaidemo.dtos.ToolChatRequest;
import com.ai.springaidemo.dtos.ToolChatResponse;
import com.ai.springaidemo.tools.DateTimeTool;
import com.ai.springaidemo.tools.EmployeeTool;
import jakarta.validation.Valid;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ToolCallingChatController {

    private final ChatClient chatClient;

    public ToolCallingChatController(
    	ChatClient.Builder builder, 
    	EmployeeTool employeeTool,
    	DateTimeTool dateTimeTool
    ) {
        this.chatClient = builder
                .defaultSystem("""
                Bạn là trợ lý ảo hữu ích của công ty Phuc.
                Bạn luôn trả lời dựa trên dữ liệu lấy được từ các công cụ (tools) được cung cấp.
                Nếu không tìm thấy thông tin hoặc không biết câu trả lời, hãy trả lời là "Tôi không biết".
                """)
                .defaultTools(
                	employeeTool, 
                	dateTimeTool
                )
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    @PostMapping
    public ToolChatResponse chat(
    	@RequestBody
    	@Valid ToolChatRequest request
    ) {
        String response = chatClient
                .prompt()
                .user(request.prompt())
                .call()
                .content();
        return new ToolChatResponse(response);
    }
}
