package com.devsuperior.bds02.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Builder
@Getter
@Setter
public class StandardError {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
}
