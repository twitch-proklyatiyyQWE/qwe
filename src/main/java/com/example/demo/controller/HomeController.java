package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String getHome() {
        return "home";
    }
    @GetMapping("/calc")
    public String showCalcPage() {
        return "calc";
    }
    @PostMapping("/calc")
    public String calculate(Model model,
                            @RequestParam("num1") double num1,
                            @RequestParam("num2") double num2,
                            @RequestParam("operation") String operation) {
        double result = 0;
        switch (operation) {
            case "add":
                result = num1 + num2;
                break;
            case "subtract":
                result = num1 - num2;
                break;
            case "multiply":
                result = num1 * num2;
                break;
            case "divide":
                if (num2 != 0) {
                    result = num1 / num2;
                }
                break;
        }
        model.addAttribute("result", result);
        return "calc";
    }
    @GetMapping("/converter")
    public String converter() {
        return "converter";
    }
    @PostMapping("/converter")
    public String convertCurrency(Model model,
                                  @RequestParam("fromCurrency") String fromCurrency,
                                  @RequestParam("toCurrency") String toCurrency,
                                  @RequestParam("amount") double amount) {

        double conversionRate = getConversionRate(fromCurrency, toCurrency);
        double convertedAmount = amount * conversionRate;

        model.addAttribute("convertedAmount", convertedAmount);
        return "converter";
    }
    private double getConversionRate(String fromCurrency, String toCurrency) {
        if (fromCurrency.equals("USD") && toCurrency.equals("EUR")) {
            return 0.85;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("USD")) {
            return 1.18;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("RUB")) {
            return 33.0;
        }
        return 1.0;
    }
}
