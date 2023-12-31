package com.example.multimodule.application;

import com.example.multimodule.service.MyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Because DemoApplication is inside a different package (com.example.multimodule.application) than MyService (com.example.multimodule.service), @SpringBootApplication cannot automatically detect it. There are different ways to let `MyService be picked up:
 *
 * Import it directly with @Import(MyService.class).
 *
 * Fetch everything from its package by using @SpringBootApplication(scanBasePackageClasses={…​}).
 *
 * Specifying the parent package by name: com.example.multimodule. (This guide uses this method)
 *
 If your application also uses JPA or Spring Data, the @EntityScan and @EnableJpaRepositories (and related) annotations inherit only their base package from @SpringBootApplication when not explicitly specified. That is, once you specify scanBasePackageClasses or scanBasePackages, you might also have to also explicitly use @EntityScan and @EnableJpaRepositories with their package scans explicitly configured.
 */
@SpringBootApplication(scanBasePackages = "com.example.multimodule")
@RestController
public class DemoApplication {
    private final MyService myService;

    public DemoApplication(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/")
    public String home() {
        return myService.message();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
