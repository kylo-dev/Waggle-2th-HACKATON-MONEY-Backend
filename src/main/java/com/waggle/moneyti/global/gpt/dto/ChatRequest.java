package com.waggle.moneyti.global.gpt.dto;

import java.util.List;
import lombok.Data;

@Data
public class ChatRequest {

    private String model;
    private List<ChatMessage> messages;
    private int n;
    private Integer max_tokens;

    public ChatRequest(String model, List<ChatMessage> messages) {
        this.model = model;
//        this.messages = new ArrayList<ChatMessage>();
//        this.messages.add(new ChatMessage("user", prompt));
        this.messages = messages;

        this.n = 1;
    }

}
