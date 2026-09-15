package com.liban.aisupporttickettriageapi.services;

import com.liban.aisupporttickettriageapi.model.enums.Category;
import com.liban.aisupporttickettriageapi.model.enums.Priority;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AiTriageServiceImpl implements AiTriageService {

    private final ChatModel chatModel;

    public AiTriageServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @Override
    public String analyzeTicket(String title, String description) {

        String systemMessage = "You are an AI support assistant. Analyze the following support ticket:\n" +
                "Title: " + title + "\n" +
                "Description: " + description + "\n";

        String categories = "(choose one: " + Category.BUG + ", " + Category.ACCOUNT + ", "
                + Category.FEATURE_REQUEST + ", " + Category.BILLING + ")";

        String priorities = "(choose one: " + Priority.HIGH + ", " +  Priority.MEDIUM + ", " + Priority.LOW + ", "
                + Priority.LOW + ", " + Priority.URGENT + ")";

        String prompt = systemMessage +
                "Return a JSON response with: \n" +
                "1. category " + categories + "\n" +
                "2. priority " + priorities + "\n" +
                "3. aiSuggestedReply ( A polite, helpful 2-sentence draft for the support team to the user)";
        
        return cleanJsonOutput(Objects.requireNonNull(chatModel.call(prompt)));
    }

    private String cleanJsonOutput(String prompt) {
        if (prompt.isEmpty()) {
            return "";
        }

        String cleaned = prompt.trim();

        if (cleaned.startsWith("```")) {
            cleaned = cleaned.replaceAll("^```(?:json)?", "")
                    .replaceAll("```$", "")
                    .trim();
        }

        return cleaned;
    }
}
