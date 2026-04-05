package com.parkalot.api.parking_space;

import com.parkalot.api.BaseRepository;
import com.parkalot.api.parking_space.Dtos.ParkingAvailabilityDto;
import com.parkalot.infrastructure.models.ParkingSpace;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParkingSpaceRepository extends BaseRepository<ParkingSpace> {
  @Query("SELECT ps FROM ParkingSpace ps WHERE ps.Garage.id = ?1")
  List<ParkingSpace> findByGarageId(int garageId);

  @Query(
    """
        SELECT new com.parkalot.api.parking_space.Dtos.ParkingAvailabilityDto(
            COUNT(ps),
            SUM(CASE WHEN sd.Status = 0 THEN 1 ELSE 0 END)
        )
        FROM ParkingSpace ps
        LEFT JOIN ps.Sensor sd
        WHERE ps.Garage.id = :garageId
    """
  )
  Optional<ParkingAvailabilityDto> checkAvailability(
    @Param("garageId") int garageId
  );
}
