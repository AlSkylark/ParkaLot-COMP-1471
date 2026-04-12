package com.parkalot.api.price_type;

import com.parkalot.infrastructure.enums.SpaceType;
import com.parkalot.infrastructure.models.PriceType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Component;

@Component
public class PriceBuilder {

  private final PriceTypeRepository repo;
  private final int BULKY_DAY_COUNT = 10;
  private final String HOURLY = "Hourly";
  private final String FULLDAY = "FullDay";
  private final String BULKY = "Bulk";

  private boolean multiDay = false;
  private boolean isBulky = false;
  private SpaceType type = SpaceType.NORMAL;

  public PriceBuilder(PriceTypeRepository repo) {
    this.repo = repo;
  }

  public PriceBuilder days(LocalDate dateFrom, LocalDate dateTo) {
    var amount = ChronoUnit.DAYS.between(dateFrom, dateTo);
    if (amount > 1) {
      multiDay = true;
    }

    if (amount >= BULKY_DAY_COUNT) {
      isBulky = true;
    }

    return this;
  }

  public PriceBuilder hours(LocalTime timeFrom, LocalTime timeTo) {
    if (timeFrom != null && timeTo != null) {
      multiDay = false;
    }

    return this;
  }

  public PriceBuilder spaceType(SpaceType type) {
    this.type = type;

    return this;
  }

  public PriceType generatePriceType() {
    var name = "";
    if (multiDay) {
      name = FULLDAY;
    } else {
      name = HOURLY;
    }

    name += "_" + type.name();

    if (isBulky) {
      name += "_" + BULKY;
    }

    var result = repo.findByName(name);

    return result.orElseGet(() -> {
      var normal = repo.findById(1);
      return normal.get();
    });
  }

  public static BigDecimal calculateTotal(
    PriceType type,
    LocalDate startDate,
    LocalDate endDate,
    LocalTime startTime,
    LocalTime endTime
  ) {
    var noDays = ChronoUnit.DAYS.between(startDate, endDate);
    noDays = noDays == 0 ? 1 : noDays;
    var totalMinutes =
      startTime != null && endTime != null
        ? ChronoUnit.MINUTES.between(startTime, endTime)
        : 24 * 60;

    var noHours = (totalMinutes / 60) + (totalMinutes % 60 > 30 ? 1 : 0);

    var bdNoDays = new BigDecimal(noDays);
    var bdNoHours = new BigDecimal(noHours);

    return type.getPrice().multiply(bdNoDays).multiply(bdNoHours);
  }
}
