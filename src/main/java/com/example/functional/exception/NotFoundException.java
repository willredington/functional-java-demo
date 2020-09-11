package com.example.functional.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "could not find entity")
public class NotFoundException extends RuntimeException {}
