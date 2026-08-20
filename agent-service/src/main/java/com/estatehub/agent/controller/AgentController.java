package com.estatehub.agent.controller;

import com.estatehub.agent.dto.AgentRequest;
import com.estatehub.agent.dto.AgentResponse;
import com.estatehub.agent.dto.ErrorResponse;
import com.estatehub.agent.dto.RatingRequest;
import com.estatehub.agent.model.AgentRating;
import com.estatehub.agent.service.AgentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agents")
@RequiredArgsConstructor
@Tag(name = "Agent", description = "The Agent API for managing real estate agents and their ratings")
@SecurityRequirement(name = "ApiKeyAuth")
public class AgentController {

    private final AgentService agentService;

    @Operation(summary = "Register a new agent", description = "Registers a new real estate agent profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Agent created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request arguments",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Missing or invalid API Key")
    })
    @PostMapping
    public ResponseEntity<AgentResponse> registerAgent(@Valid @RequestBody AgentRequest request) {
        return new ResponseEntity<>(agentService.registerAgent(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all agents", description = "Retrieves a list of all agents")
    @GetMapping
    public ResponseEntity<List<AgentResponse>> getAllAgents() {
        return ResponseEntity.ok(agentService.getAllAgents());
    }

    @Operation(summary = "Get agent by ID", description = "Retrieves a specific agent by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agent retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Agent not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @GetMapping("/{id}")
    public ResponseEntity<AgentResponse> getAgentById(@PathVariable String id) {
        return ResponseEntity.ok(agentService.getAgentById(id));
    }

    @Operation(summary = "Update agent details", description = "Updates an agent's modifiable fields")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agent updated successfully"),
            @ApiResponse(responseCode = "404", description = "Agent not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))})
    })
    @PutMapping("/{id}")
    public ResponseEntity<AgentResponse> updateAgent(@PathVariable String id, @Valid @RequestBody AgentRequest request) {
        return ResponseEntity.ok(agentService.updateAgent(id, request));
    }

    @Operation(summary = "Delete an agent", description = "Deletes an agent profile")
    @ApiResponse(responseCode = "204", description = "Agent deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgent(@PathVariable String id) {
        agentService.deleteAgent(id);
        return ResponseEntity.noContent().build();
    }
    
    @Operation(summary = "Add a rating", description = "Adds a review and rating score for an agent")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rating added successfully and agent updated"),
            @ApiResponse(responseCode = "400", description = "Invalid score (must be 1-5)"),
            @ApiResponse(responseCode = "404", description = "Agent not found")
    })
    @PostMapping("/{id}/ratings")
    public ResponseEntity<AgentResponse> addRating(@PathVariable String id, @Valid @RequestBody RatingRequest request) {
        return ResponseEntity.ok(agentService.addRating(id, request));
    }
    
    @Operation(summary = "Get agent ratings", description = "Retrieves all ratings/reviews submitted for this agent")
    @GetMapping("/{id}/ratings")
    public ResponseEntity<List<AgentRating>> getAgentRatings(@PathVariable String id) {
        return ResponseEntity.ok(agentService.getAgentRatings(id));
    }
}
