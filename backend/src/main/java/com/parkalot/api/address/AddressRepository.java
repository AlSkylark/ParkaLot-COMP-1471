package com.parkalot.api.address;

import com.parkalot.infrastructure.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {}
