package com.parkalot.api.contracts;

import com.parkalot.api.contracts.dtos.ContractDetailDto;
import com.parkalot.api.contracts.dtos.GuestData;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contracts")
public class ContractController {

  private final ContractService service;

  public ContractController(ContractService service) {
    this.service = service;
  }

  @GetMapping("/{id}")
  public ResponseEntity<ContractDetailDto> get(@PathVariable int id) {
    return service
      .getContractDetail(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(
    @PathVariable int id,
    @RequestBody ContractUpdateRequest request
  ) {
    service.updateContract(id, request.isRecurrent(), request.guestData());
    return ResponseEntity.ok(Map.of("success", true));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> cancel(@PathVariable int id) {
    service.cancelContract(id);
    return ResponseEntity.ok(Map.of("success", true));
  }

  public record ContractUpdateRequest(boolean isRecurrent, GuestData guestData) {}
}
