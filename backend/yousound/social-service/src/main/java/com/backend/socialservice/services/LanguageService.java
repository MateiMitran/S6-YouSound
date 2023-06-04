package com.backend.socialservice.services;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.language.v1.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;


@Service
public class LanguageService {

    private final LanguageServiceClient languageServiceClient;


    public LanguageService(@Value("${api.key}") String apiKey) throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(new ByteArrayInputStream(apiKey.getBytes()))
                .createScoped(LanguageServiceSettings.getDefaultServiceScopes());

        LanguageServiceSettings settings = LanguageServiceSettings.newBuilder()
                .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                .build();

        languageServiceClient = LanguageServiceClient.create(settings);
    }

    public Sentiment analyzeSentiment(String text) {
        Document document = Document.newBuilder()
                .setContent(text)
                .setType(Document.Type.PLAIN_TEXT)
                .build();

        AnalyzeSentimentRequest request = AnalyzeSentimentRequest.newBuilder()
                .setDocument(document)
                .setEncodingType(EncodingType.UTF8)
                .build();

        AnalyzeSentimentResponse response = languageServiceClient.analyzeSentiment(request);

        return response.getDocumentSentiment();
    }

    public void close() {
        if (languageServiceClient != null) {
            languageServiceClient.close();
        }
    }
}