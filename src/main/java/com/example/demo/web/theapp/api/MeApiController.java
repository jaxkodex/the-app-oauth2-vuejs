package com.example.demo.web.theapp.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/me")
public class MeApiController {

    @GetMapping(value = "")
    public String me() {
        return "hellow";
    }
}
