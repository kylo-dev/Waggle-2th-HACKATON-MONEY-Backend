package com.waggle.moneyti.global.gpt.controller;

import com.waggle.moneyti.global.gpt.service.PromptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PromptController {

    private final PromptService promptService;

    @PostMapping("/prompt")
    public String getOpenaiResponse(@RequestBody String prompt) {

        return promptService.prompt(prompt);
    }
}
