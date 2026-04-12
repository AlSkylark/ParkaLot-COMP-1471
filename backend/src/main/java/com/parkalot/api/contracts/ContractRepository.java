package com.parkalot.api.contracts;

import com.parkalot.api.BaseRepository;
import com.parkalot.infrastructure.models.Contract;
import java.util.Optional;

public interface ContractRepository extends BaseRepository<Contract> {
  Optional<Contract> findByContractNumber(String contractNumber);
}
