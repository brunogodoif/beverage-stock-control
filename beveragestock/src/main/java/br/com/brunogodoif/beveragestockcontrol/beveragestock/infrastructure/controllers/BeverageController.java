package br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers;

import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.model.Beverage;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.orchestrators.BeverageStockOrchestratorInterface;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.domain.usecases.GetBeveragesInterface;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.request.RegisterBeverageRequest;
import br.com.brunogodoif.beveragestockcontrol.beveragestock.infrastructure.controllers.response.BeverageResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/section")
    public ResponseEntity<List<BeverageResponse>> retrieveBeveragesBySection(@RequestParam Long sectionId) {
        List<Beverage> beverages = getBeveragesBySection.retrieveBeverages(sectionId);
        List<BeverageResponse> response = beverages.stream().map(BeverageResponse::fromDomain)
                                                   .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<BeverageResponse> registerBeverageInSection(@RequestBody @Valid RegisterBeverageRequest request) {
        Beverage beverage = beverageStockOrchestrator.registerBeverageAndHistory(request);
        BeverageResponse response = BeverageResponse.fromDomain(beverage);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}