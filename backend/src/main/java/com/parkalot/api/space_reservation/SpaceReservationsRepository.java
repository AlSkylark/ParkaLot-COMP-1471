package com.parkalot.api.space_reservation;

import com.parkalot.api.BaseRepository;
import com.parkalot.infrastructure.models.SpaceReservation;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpaceReservationsRepository
  extends BaseRepository<SpaceReservation>
{
  @Query(
    """
    SELECT sr FROM SpaceReservation sr
    LEFT JOIN sr.space ps
    WHERE ps.garage.id = :garageId
    AND sr.datefrom <= :date AND sr.dateto >= :date
    """
  )
  List<SpaceReservation> findByGarageId(
    @Param("garageId") int garageId,
    @Param("date") LocalDate date
  );

  @Query(
    """
    SELECT sr FROM SpaceReservation sr
    WHERE sr.datefrom <= :date AND sr.dateto >= :date
    """
  )
  List<SpaceReservation> findByDate(@Param("date") LocalDate date);
}
