package com.estatehub.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Standardized Error Response")
public class ErrorResponse {
    @Schema(description = "Error Status Code")
    private int status;
    @Schema(description = "Error Message")
    private String error;
    @Schema(description = "Detailed Message")
    private String message;
}
