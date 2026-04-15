package com.parkalot.api.contracts.dtos;

import java.time.LocalDate;

public record ContractDto(QuoteDto quote, LocalDate invoiceDate) {}
