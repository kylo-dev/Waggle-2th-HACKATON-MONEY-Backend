package com.waggle.moneyti.global.gpt.controller;

import com.waggle.moneyti.global.gpt.service.PromptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommends")
public class PromptController {

    private final PromptService promptService;

    @PostMapping
    public String getOpenaiResponse(@RequestParam(name = "recommendId") Long recommendId) {

        return promptService.prompt(recommendId);
    }
}
