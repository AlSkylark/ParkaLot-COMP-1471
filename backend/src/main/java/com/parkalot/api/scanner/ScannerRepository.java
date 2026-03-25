package com.parkalot.api.scanner;

import com.parkalot.infrastructure.models.Scanner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScannerRepository extends JpaRepository<Scanner, Integer> {}
