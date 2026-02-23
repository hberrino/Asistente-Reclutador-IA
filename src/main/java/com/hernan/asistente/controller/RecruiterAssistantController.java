package com.hernan.asistente.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hernan.asistente.dto.CvAnalysisRequest;
import com.hernan.asistente.dto.CvAnalysisResponse;
import com.hernan.asistente.service.RecruiterService;

import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/recruiter")
@RequiredArgsConstructor
public class RecruiterAssistantController {

    private final RecruiterService service;

    @PostMapping("/analyze")
   public CvAnalysisResponse analyze(@RequestBody CvAnalysisRequest request) {
    return service.analyze(request.cvText(), request.jobDescription());
}
}