package com.hernan.asistente.dto;

import java.util.List;

public record CvAnalysisResponse(
    String professionalSummary,
    List<String> detectedSkills,
    String estimatedSeniority,
    int matchPercentage,
    List<String> strengths,
    List<String> gaps,
    List<String> recommendations
) {}