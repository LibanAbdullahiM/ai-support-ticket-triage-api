package com.liban.aisupporttickettriageapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String home() {
         return "Welcome to AIS Support Ticket Triage API!. Sorry We are under progress Yet." +
                "We will be online soon. See soon!";
    }
}