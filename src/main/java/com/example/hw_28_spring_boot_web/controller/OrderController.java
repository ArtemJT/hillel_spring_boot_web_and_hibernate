package com.example.hw_28_spring_boot_web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ping")
@Slf4j
public class InitializerController {

    @GetMapping
    public String ping() {
        log.info("PING WAS CALLED");
        return "OK";
    }
}
