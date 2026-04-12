package com.parkalot.api.parking_space;

import com.parkalot.api.BaseRepository;
import com.parkalot.api.parking_space.Dtos.ParkingAvailabilityDto;
import com.parkalot.infrastructure.models.ParkingSpace;
import jakarta.persistence.QueryHint;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

public interface ParkingSpaceRepository extends BaseRepository<ParkingSpace> {
  @Query("SELECT ps FROM ParkingSpace ps WHERE ps.garage.id = ?1")
  List<ParkingSpace> findByGarageId(int garageId);

  @Query(
    """
        SELECT new com.parkalot.api.parking_space.Dtos.ParkingAvailabilityDto(
            COUNT(ps),
            SUM(CASE WHEN sd.status = 0 THEN 1 ELSE 0 END)
        )
        FROM ParkingSpace ps
        LEFT JOIN ps.sensor sd
        WHERE ps.garage.id = :garageId
    """
  )
  Optional<ParkingAvailabilityDto> checkAvailability(
    @Param("garageId") int garageId
  );

  @Query(
    """
        SELECT new com.parkalot.api.parking_space.Dtos.ParkingAvailabilityDto(
            COUNT(ps),
            SUM(CASE WHEN sd.status = 0 THEN 1 ELSE 0 END)
        )
        FROM ParkingSpace ps
        LEFT JOIN ps.sensor sd
        WHERE ps.garage.id = :garageId
        AND ps.spacetype = :spaceType
    """
  )
  Optional<ParkingAvailabilityDto> checkAvailability(
    @Param("garageId") int garageId,
    @Param("spaceType") int spaceType
  );

  @Query(
    """
    SELECT ps FROM ParkingSpace ps WHERE ps.garage.id = :garageId
    AND ps.id NOT IN (
      SELECT sr.space.id FROM SpaceReservation sr WHERE sr.datefrom >= :dateFrom AND sr.dateto <= :dateTo
    )
    """
  )
  List<ParkingSpace> findAvailableSpace(
    @Param("garageId") int garageId,
    @Param("dateFrom") LocalDate dateFrom,
    @Param("dateTo") LocalDate dateTo
  );

  @Query(
    """
    SELECT ps FROM ParkingSpace ps WHERE ps.garage.id = :garageId
    AND ps.id NOT IN (
      SELECT sr.space.id FROM SpaceReservation sr WHERE sr.datefrom >= :date AND sr.dateto <= :date AND
      sr.timefrom >= :timefrom AND sr.timeto <= :timeTo
    )
    """
  )
  List<ParkingSpace> findAvailableSpace(
    @Param("garageId") int garageId,
    @Param("dateFrom") LocalDate date,
    @Param("timeFrom") LocalTime timeFrom,
    @Param("timeTo") LocalTime timeTo
  );
}
