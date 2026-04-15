package com.parkalot.api.contracts;

import com.parkalot.api.BaseRepository;
import com.parkalot.infrastructure.models.Contract;
import jakarta.transaction.Transactional;
import java.util.Optional;

public interface ContractRepository extends BaseRepository<Contract> {
  Optional<Contract> findByContractNumber(String contractNumber);

  @Transactional
  void deleteByContractNumber(String contractNumber);
}
