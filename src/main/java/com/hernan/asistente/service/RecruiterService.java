package com.hernan.asistente.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.hernan.asistente.dto.CvAnalysisResponse;

@Service
public class RecruiterService {

    private final ChatClient chatClient;

    public RecruiterService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public CvAnalysisResponse analyze(String cv, String job) {

        if (cv == null || job == null) {
            throw new IllegalArgumentException("CV y descripcion del trabajo son requeridos");
        }

        if (cv.length() > 4000) {
            cv = cv.substring(0, 4000);
        }

        if (job.length() > 4000) {
            job = job.substring(0, 4000);
        }

        String prompt = """
You are a senior technical recruiter with 15+ years of experience evaluating software engineering candidates.

Your task is to critically and realistically evaluate how well the candidate matches the job description.

Be strict: do NOT inflate the match score. Penalize heavily missing core technologies, insufficient experience, or mismatched seniority. Do not reward generic experience, courses, or certifications in place of real experience. Multiple missing key requirements must reduce the match percentage below 50.

Return your response ONLY as valid JSON matching this exact structure on spanish:

{{
  "professionalSummary": "string max 120 words describing candidate's profile and relevance",
  "detectedSkills": ["string"],
  "estimatedSeniority": "Junior | Semi Senior | Senior",
  "matchPercentage": number (0-100),
  "strengths": ["string"],
  "gaps": ["string describing missing or weak areas"],
  "recommendations": ["string actionable advice for candidate improvement or fit"]
}}

Do NOT include any extra text outside the JSON.

CV:
%s

Job Description:
%s
""".formatted(cv, job);

        try {
            return chatClient
                    .prompt()
                    .user(prompt)
                    .call()
                    .entity(CvAnalysisResponse.class);

        } catch (Exception ex) {
            throw new RuntimeException("Asistente IA fallo al analizar el CV", ex);
        }
    }
}