package com.ArcadeBoot.WaterTask.domin.dto;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
