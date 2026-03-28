package com.example.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController("/")
public class SpaceCraftController {

  private static final Logger log = LoggerFactory.getLogger(SpaceCraftController.class);
  private List<SpaceCraft> spaceCrafts = new ArrayList<>(Arrays.asList(
      new SpaceCraft(0, "Enterprice", "ID-697"),
      new SpaceCraft(1, "Millenium Falcon", "1")
  ));

  @Tag(name = "spacecrafts")
  @Operation(summary = "Get all spacecrafts")
  @GetMapping(path = "/spacecrafts", produces = "application/json")
  public ResponseEntity<List<SpaceCraft>> getSpaceCrafts() {
    return new ResponseEntity<>(spaceCrafts, HttpStatus.OK);
  }

  @Tag(name = "spacecrafts")
  @Operation(summary = "Get the spacecraft with the specified id")
  @ApiResponse(
      responseCode = "200",
      content = {
          @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = SpaceCraft.class)
          )
      }
  )
  @ApiResponse(
      responseCode = "404",
      description = "SpaceCraft not found",
      content = {@Content()}
  )
  @GetMapping(path = "/spacecrafts/{id}", produces = "application/json")
  public ResponseEntity<SpaceCraft> getSpaceCraft(
      @Parameter(description = "Id of the specified spacecraft")
      @PathVariable int id
  ) {
    SpaceCraft spaceCraft = spaceCrafts.get(id);
    if (spaceCraft != null) {
      return new ResponseEntity<>(spaceCraft, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @Tag(name = "spacecrafts")
  @Operation(summary = "Add a spacecraft",
      description = "This will add a spacecraft and give it a new id"
  )
  @PostMapping(path = "/spacecrafts", consumes = "application/json", produces = "application/json")
  public ResponseEntity<SpaceCraft> addSpaceCraft(@RequestBody SpaceCraft spaceCraft) {
    int amountOfSpaceCrafts = spaceCrafts.size();
    SpaceCraft newSpaceCraft = new SpaceCraft(amountOfSpaceCrafts, spaceCraft.name(), spaceCraft.tailId());
    spaceCrafts.add(newSpaceCraft);
    return new ResponseEntity<>(newSpaceCraft, HttpStatus.OK);
  }

  @ApiResponse(
      responseCode = "418",
      description = "If called upon return 418"
  )
  @GetMapping("spacecrafts/brewcoffee")
  public ResponseEntity<String> teaPot() {
    return new ResponseEntity<>("I'm a teapot", HttpStatus.I_AM_A_TEAPOT);
  }
}
