package com.example.eversign_integration.controller;

import com.example.eversign_integration.service.EversignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private final EversignService eversignService;

    public DocumentController(EversignService eversignService) {
        this.eversignService = eversignService;
    }

    @GetMapping("/{documentId}")
    public ResponseEntity<String> getDocument(@PathVariable String documentId,
                                              @RequestParam String type) {
        String result = eversignService.getDocument(documentId, type);
        return ResponseEntity.ok(result);
    }
}
