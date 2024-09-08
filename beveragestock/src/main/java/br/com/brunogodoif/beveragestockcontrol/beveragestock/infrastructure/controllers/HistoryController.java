package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.History;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.orchestrators.RegisterHistoryOrchestratorInterface;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.request.RegisterHistoryRequest;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.response.HistoryResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final RegisterHistoryOrchestratorInterface registerHistoryOrchestrator;

    public HistoryController(RegisterHistoryOrchestratorInterface registerHistoryOrchestrator) {
        this.registerHistoryOrchestrator = registerHistoryOrchestrator;
    }

    @PostMapping("/register")
    public ResponseEntity<HistoryResponse> registerHistory(@RequestBody @Valid RegisterHistoryRequest request) {
        History history = registerHistoryOrchestrator.registerHistory(request);
        HistoryResponse response = HistoryResponse.fromDomain(history);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}