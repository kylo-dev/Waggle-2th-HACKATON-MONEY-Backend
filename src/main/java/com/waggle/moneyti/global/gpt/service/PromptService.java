package com.waggle.moneyti.global.gpt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waggle.moneyti.domain.MoneyTI.MoneyTI;
import com.waggle.moneyti.domain.MoneyTI.repository.MoneyTIRepository;
import com.waggle.moneyti.global.config.ChatGPTConfig;
import com.waggle.moneyti.global.gpt.dto.ChatMessage;
import com.waggle.moneyti.global.gpt.dto.ChatRequest;
import com.waggle.moneyti.global.gpt.dto.ChatResponse;
import com.waggle.moneyti.global.gpt.dto.ChatResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PromptService {

    private final ChatGPTConfig chatGPTConfig;
    private final MoneyTIRepository moneyTIRepository;

    @Value("${chatgpt.model}")
    private String model;

    @Value("${chatgpt.url}")
    private String url;

    public ChatResultResponse prompt(String moneyTI) throws ParseException {
        // 토큰 정보가 포함된 Header 가져오기
        HttpHeaders headers = chatGPTConfig.httpHeaders();

        // Create request
        ChatRequest chatRequest = generateChatPrompt(moneyTI);
        HttpEntity<ChatRequest> requestEntity = new HttpEntity<>(chatRequest, headers);

        RestTemplate restTemplate = new RestTemplate();
        ChatResponse response = restTemplate.postForObject(url, requestEntity, ChatResponse.class);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            throw new RuntimeException();
        }
        String result =  response.getChoices().get(0).getMessage().getContent();

        JSONParser jsonParser = new JSONParser(result);
        Object obj = jsonParser.parse();
        ObjectMapper mapper = new ObjectMapper();
        JSONObject jsonObj = mapper.convertValue(obj, JSONObject.class);
        return new ChatResultResponse((String) jsonObj.get("recommendHabit"), (String) jsonObj.get("recommendProduct"));
    }

    private ChatRequest generateChatPrompt(String request) {

        // GPT 프롬프트 엔지니어링 부분
        List<ChatMessage> messages = new ArrayList<>();

        // GPT에 역할 설명 (페르소나 부여)
        String roleExplain =
            "당신은 재테크의 여러 분야와 좋은 소비 습관에 대해 가장 잘 알고 있는 사람입니다." +
                "주식, 저축, 비트코인, 보험, 복권 등 다양한 방법으로 돈을 벌었습니다." +
                "자신의 소비성향에 따라 올바른 금융상품과 현명한 소비습관을 알고 있습니다." +
                "서너는 2~3개의 설문지와 적절한 소비성향을 통해 사람들에게 잘 맞는 금융상품을 추천합니다.";

        messages.add(ChatMessage.builder()
            .role("system")
            .content(roleExplain)
            .build());

        //GPT에 답변 형식 제공 (Form)
        messages.add(ChatMessage.builder()
            .role("system")
            .content(
                "당신은 고객의 특성을 분석하여 가장 잘 맞는 금융상품과 소비습관을 추천하는 임무를 맡고 있습니다." +
                    "당신은 결과 추천으로 \"recommendHabit\"과 \"recommendProduct\"의 두 가지 구성 요소로 JSON 형식으로 추천합니다."
                    +
                    " \"소비습관\", \"추천 상품\"을 간략하게 2줄로 추천과 설명의 글을 작성해줘.")
            .build());

        //GPT에 유저 정보 제공 (Context)
        MoneyTI moneyTI = moneyTIRepository.findByName(request).get();

        String userInfo = generateUserInfo(moneyTI.getPrompt());

        messages.add(ChatMessage.builder()
            .role("system")
            .content(userInfo)
            .build());

        //GPT에 질문 (작업)
        messages.add(ChatMessage.builder()
            .role("user")
            .content("나에게 맞는 금융상품과 현명한 소비습관을 추천해주고 JSON 형식으로 반환해주세요.")
            .build());

        ChatRequest chatRequest = new ChatRequest(model, messages);

        return chatRequest;
    }

    //유저 입력 정보를 GPT에 제공하기 위한 문자열 생성 메소드
    private String generateUserInfo(String content) {

        log.info("========= content : " + content);
        String[] contents = content.split(",");

        //순서:
        String userInfo = String.format("\"사용자의 특징은 \"%s\""
                + ", \"%s\""
                + ", \"%s\" 사람이라고 할 수 있어."
            , contents[0], contents[1], contents[2]);

        log.info("=========== usrInfo : " + userInfo);
        return userInfo;
    }

}
