package com.example.hw_28_spring_boot_web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.hw_28_spring_boot_web.utilities.Logger.logInvokedMethod;

@RestController
@RequestMapping("ping")
public class PingController {

    @GetMapping
    public ResponseEntity<String> ping() {
        logInvokedMethod();
        return ResponseEntity.ok("OK");
    }
}
