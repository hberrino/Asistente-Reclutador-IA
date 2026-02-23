package com.hernan.asistente.dto;

import jakarta.validation.constraints.NotBlank;

public record CvAnalysisRequest(
        @NotBlank String cvText,
        @NotBlank String jobDescription
) {}