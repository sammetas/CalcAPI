package com.example.samm.CalcAPI.controller;


import com.example.samm.CalcAPI.CalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("api/v1")
public class CalculatorAPI {

    private final CalculatorService service;

    public CalculatorAPI(CalculatorService service) {
        this.service = service;
    }

    @GetMapping("/calc")
    public ResponseEntity<String> getCalcResult(@RequestBody String expression) {

        if (Objects.isNull(expression) || expression == "") {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.calculate(expression));
    }

}
