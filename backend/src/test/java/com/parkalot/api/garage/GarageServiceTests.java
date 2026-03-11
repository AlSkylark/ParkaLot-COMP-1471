package com.parkalot.api.garage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.parkalot.infrastructure.models.Garage;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GarageServiceTests {

  @Mock
  private GarageRepository fakeRepo;

  private GarageService service;

  @BeforeEach
  void SetUp() {
    this.service = new GarageService(fakeRepo);
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

  private List<Garage> GetFakeGarages() {
    var list = new ArrayList<Garage>();
    var garage1 = new Garage();
    garage1.Id = 1;
    garage1.Name = "Test1";

    var garage2 = new Garage();
    garage2.Id = 2;
    garage2.Name = "Test2";

    list.add(garage1);
    list.add(garage2);

    return list;
  }
}
