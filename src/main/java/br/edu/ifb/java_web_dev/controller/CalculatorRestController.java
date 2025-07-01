package br.edu.ifb.java_web_dev.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifb.java_web_dev.model.CalculatorService;



@RestController
@RequestMapping("calculator")
@CrossOrigin
public class CalculatorRestController {

    @GetMapping(value = "/somar/{a}/{b}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Integer somar(@PathVariable Integer a, @PathVariable Integer b) {
        return CalculatorService.somar(a, b);
    }

    @GetMapping(value = "/subtrair/{a}/{b}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Integer subtrair(@PathVariable Integer a, @PathVariable Integer b) {
        return CalculatorService.subtrair(a, b);
    }

    @GetMapping(value = "/multiplicar/{a}/{b}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Integer multiplicar(@PathVariable Integer a, @PathVariable Integer b) {
        return CalculatorService.multiplicar(a, b);
    }

    @GetMapping(value = "/dividir/{a}/{b}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody double dividir(@PathVariable Integer a, @PathVariable Integer b) {
        return CalculatorService.dividir(a, b);
    }
}