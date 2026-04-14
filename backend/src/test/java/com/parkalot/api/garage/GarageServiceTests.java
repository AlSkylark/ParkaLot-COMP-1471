package com.parkalot.api.garage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.parkalot.api.parking_space.ParkingSpaceService;
import com.parkalot.api.scanner.ScannerRepository;
import com.parkalot.infrastructure.enums.GarageAvailability;
import com.parkalot.infrastructure.models.Garage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GarageServiceTests {

  @Mock
  private GarageRepository fakeRepo;

  @Mock
  private ParkingSpaceService fakeParkingSpaceService;

  private ScannerRepository fakeScannerRepository;

  private GarageService service;

  @BeforeEach
  void SetUp() {
    this.service = new GarageService(
      fakeRepo,
      fakeParkingSpaceService,
      fakeScannerRepository
    );
  }

  @Test
  void GetAllGarages_CallsRepositoryAndCreatesDtos_Correctly() {
    //arrange
    when(fakeRepo.findAll()).thenReturn(GetFakeGarages());

    //act
    var result = service.GetAllGarages();

    //assert
    verify(fakeRepo, times(1)).findAll();
    assertThat(result).hasSize(2);
    assertThat(result.getFirst().name()).isEqualTo("Test1");
    assertThat(result.getFirst().address()).isEqualTo("");
  }

  @Test
  void GetGarage_ReturnsCorrectDto_WhenGarageExists() {
    // arrange
    var fakeGarage = new Garage();
    fakeGarage.setId(1);
    fakeGarage.setName("Test1");

    when(fakeRepo.findById(1)).thenReturn(Optional.of(fakeGarage));
    when(fakeParkingSpaceService.CheckAvailabilityForGarage(1)).thenReturn(
      GarageAvailability.HIGH_AVAILABILITY
    );
    when(fakeParkingSpaceService.GetAllSpacesForGarage(1)).thenReturn(
      new ArrayList<>()
    );

    // act
    var result = service.GetGarage(1);

    // assert
    assertThat(result).isPresent();
    assertThat(result.get().name()).isEqualTo("Test1");
    assertThat(result.get().availability()).isEqualTo(
      GarageAvailability.HIGH_AVAILABILITY
    );
    verify(fakeRepo, times(1)).findById(1);
    verify(fakeParkingSpaceService, times(1)).CheckAvailabilityForGarage(1);
    verify(fakeParkingSpaceService, times(1)).GetAllSpacesForGarage(1);
  }

  @Test
  void GetGarage_ReturnsEmpty_WhenGarageDoesNotExist() {
    // arrange
    when(fakeRepo.findById(99)).thenReturn(Optional.empty());

    // act
    var result = service.GetGarage(99);

    // assert
    assertThat(result).isEmpty();
    verify(fakeRepo, times(1)).findById(99);
  }

  private List<Garage> GetFakeGarages() {
    var list = new ArrayList<Garage>();
    var garage1 = new Garage();
    garage1.setId(1);
    garage1.setName("Test1");

    var garage2 = new Garage();
    garage2.setId(2);
    garage2.setName("Test2");

    list.add(garage1);
    list.add(garage2);

    return list;
  }
}
