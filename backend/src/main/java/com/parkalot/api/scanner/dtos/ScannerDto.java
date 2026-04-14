package com.parkalot.api.scanner.dtos;

import com.parkalot.infrastructure.enums.ScannerType;

public record ScannerDto(int id, ScannerType type) {}
