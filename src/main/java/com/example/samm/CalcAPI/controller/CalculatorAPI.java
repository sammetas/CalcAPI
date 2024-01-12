package com.example.samm.CalcAPI.controller;


import com.example.samm.CalcAPI.CalculatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/calc1")
    public ResponseEntity<String> getCalcResult1(@RequestParam  String expression) {

        if (Objects.isNull(expression) || expression == "") {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.calculate(expression));
    }

    @GetMapping("/calc2/{expression}")
    public ResponseEntity<String> getCalcResult2(@PathVariable  String expression) {

        if (Objects.isNull(expression) || expression == "") {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.calculate(expression));
    }

}
