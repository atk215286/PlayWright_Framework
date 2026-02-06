package com.playwright.core.Ollama;

import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SelfHealingClient {

    private static final String OLLAMA_URL = "http://localhost:11434/api/generate";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private final OkHttpClient client = new OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .build();

    public String getHealedLocator(String brokenLocator, String dom) {
        try {
            // Build prompt
            String prompt = buildPrompt(brokenLocator, dom);
            System.out.println(" Prompt sent to DeepSeek:");
            System.out.println(prompt);
            // Build JSON body safely (no unescaped newlines)
            JSONObject body = new JSONObject();
            body.put("model", "deepseek-r1:8b");
            body.put("prompt", prompt);
            body.put("stream", false);

            RequestBody requestBody = RequestBody.create(body.toString(), JSON);

            Request request = new Request.Builder()
                    .url(OLLAMA_URL)
                    .post(requestBody)
                    .build();

            Response response = client.newCall(request).execute();
            System.out.println(" Response code from DeepSeek: " + response.code());
            String responseBody = response.body().string();

            System.out.println(" Raw response from DeepSeek:");
            System.out.println(responseBody);

            return extractHealedLocator(responseBody);

        } catch (Exception e) {
            System.out.println(" Healing failed: " + e.getMessage());
            return brokenLocator; // fallback to original
        }
    }

    // ---------------------------------------------------------
    // Build prompt
    // ---------------------------------------------------------
    private String buildPrompt(String brokenLocator, String dom) {
        return "You are an expert Playwright automation engineer. " +
                "Given the DOM and a broken Playwright locator, return ONLY the corrected Playwright selector.\n\n" +
                "DOM:\n" + dom + "\n\n" +
                "Broken locator: \"" + brokenLocator + "\"\n\n" +
                "Return only the corrected locator, nothing else.";
    }

    // ---------------------------------------------------------
    // Extract healed locator from DeepSeek response
    // ---------------------------------------------------------
    private String extractHealedLocator(String responseBody) {
        JSONObject json = new JSONObject(responseBody);

        // Case 1: DeepSeek returns { "response": "..." }
        if (json.has("response")) {
            return json.getString("response").trim();
        }

        // Case 2: DeepSeek returns { "message": { "content": "..." } }
        if (json.has("message")) {
            JSONObject msg = json.getJSONObject("message");
            if (msg.has("content")) {
                return msg.getString("content").trim();
            }
        }

        // Case 3: Unexpected format
        throw new RuntimeException("Healing failed: No valid locator found in response");
    }
}