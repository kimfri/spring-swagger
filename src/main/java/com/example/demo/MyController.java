package com.example.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController("/demo")
public class MyController {

  @Tag(name = "A cool tag")
  @GetMapping
  public ResponseEntity<String> helloWorld() {
    if (System.currentTimeMillis() % 2 == 0) {
      return new ResponseEntity<>("Whooo", HttpStatus.OK);
    }
    return new ResponseEntity<>("doh", HttpStatus.BAD_REQUEST);
  }

  @Schema(name = "Price", example = "$500", requiredMode = Schema.RequiredMode.REQUIRED)
  private String param;

  @Tag(name = "A cool tag!")
  @Operation(summary = "Min beskrivning av denna endpoint")
  @GetMapping(path = "/{param}")
  public ResponseEntity<String> hello2(
      @Parameter(description = "Den parameter vi söker efter")
      @RequestParam(name = "param", required = true)
      String param) {
    return ResponseEntity.ok("found: " + param);
  }
}
