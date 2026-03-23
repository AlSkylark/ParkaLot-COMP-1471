package com.parkalot.api.price_type;

import com.parkalot.infrastructure.models.PriceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceTypeRepository
  extends JpaRepository<PriceType, Integer> {}
