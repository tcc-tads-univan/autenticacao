package br.ufpr.tads.univan.auth.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {
    // write a endpoint that returns "Hello" + the query param string
    // ex: /api/v1/demo/hello?name=John
    // returns "Hello John"
    @GetMapping
    public ResponseEntity<String> helloName(String name) {
        return ResponseEntity.ok("Hello " + name);
    }
}
