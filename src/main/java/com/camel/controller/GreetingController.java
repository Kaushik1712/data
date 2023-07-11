package com.camel.controller;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("http://localhost:8083")
@RestController
public class GreetingController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @PostMapping("/greet")
    public ResponseEntity<String> greet(@RequestBody String name) {
        String result = producerTemplate.requestBody("direct:greet", name, String.class);
        return ResponseEntity.ok(result);
    }
}
