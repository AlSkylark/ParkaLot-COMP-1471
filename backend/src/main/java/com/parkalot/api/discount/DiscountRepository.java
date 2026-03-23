package com.parkalot.api.discount;

import com.parkalot.infrastructure.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {}
