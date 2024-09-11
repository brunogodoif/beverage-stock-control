package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Beverage;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.orchestrators.BeverageStockOrchestratorInterface;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.usecases.GetBeveragesInterface;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.request.RegisterBeverageRequest;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.response.BeverageResponse;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Bebidas", description = "Operações relacionadas ao gerenciamento de bebidas no estoque")
@RestController
@RequestMapping("/beverages")
public class BeverageController {

    private final BeverageStockOrchestratorInterface beverageStockOrchestrator;
    private final GetBeveragesInterface getBeveragesBySection;

    public BeverageController(BeverageStockOrchestratorInterface beverageStockOrchestrator,
                              GetBeveragesInterface getBeveragesBySection
                             ) {
        this.beverageStockOrchestrator = beverageStockOrchestrator;
        this.getBeveragesBySection = getBeveragesBySection;
    }

    @Operation(summary = "Listar bebidas por seção", description = "Retorna uma lista de bebidas armazenadas em uma seção específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de bebidas retornada com sucesso",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = BeverageResponse.class))),
            @ApiResponse(responseCode = "404", description = "Seção não encontrada",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/section")
    public ResponseEntity<List<BeverageResponse>> retrieveBeveragesBySection(@RequestParam Long sectionId) {
        List<Beverage> beverages = getBeveragesBySection.retrieveBeverages(sectionId);
        List<BeverageResponse> response = beverages.stream().map(BeverageResponse::fromDomain)
                                                   .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Registrar bebida no estoque", description = "Registra uma nova bebida em uma seção específica do estoque.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bebida registrada com sucesso",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = BeverageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Bebida ou seção não encontrada",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping()
    public ResponseEntity<BeverageResponse> registerBeverageInSection(@RequestBody @Valid RegisterBeverageRequest request) {
        Beverage beverage = beverageStockOrchestrator.registerBeverageAndHistory(request);
        BeverageResponse response = BeverageResponse.fromDomain(beverage);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}