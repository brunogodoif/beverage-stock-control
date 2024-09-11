package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.History;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.orchestrators.RegisterHistoryOrchestratorInterface;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.request.RegisterHistoryRequest;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.response.ErrorResponse;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.response.HistoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Histórico", description = "Operações relacionadas ao registro de histórico de entrada e saída de bebidas.")
@RestController
@RequestMapping("/history")
public class HistoryController {

    private final RegisterHistoryOrchestratorInterface registerHistoryOrchestrator;

    public HistoryController(RegisterHistoryOrchestratorInterface registerHistoryOrchestrator) {
        this.registerHistoryOrchestrator = registerHistoryOrchestrator;
    }

    @Operation(summary = "Registrar histórico de entrada ou saída de bebida", description = "Cria um registro de histórico de entrada ou saída de bebida, dependendo do tipo de operação (ENTRY ou EXIT).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                         description = "Histórico registrado com sucesso",
                         content = {
                    @Content(mediaType = "application/json",
                                             schema = @Schema(implementation = HistoryResponse.class))
            }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                        content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Bebida ou seção não encontrada",
                        content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping("/register")
    public ResponseEntity<HistoryResponse> registerHistory(@RequestBody @Valid RegisterHistoryRequest request) {
        History history = registerHistoryOrchestrator.registerHistory(request);
        HistoryResponse response = HistoryResponse.fromDomain(history);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}