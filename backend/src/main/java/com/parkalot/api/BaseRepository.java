package com.parkalot.api;

import com.parkalot.infrastructure.models.BaseModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<
  T extends BaseModel
> extends JpaRepository<T, Integer> {
  List<T> findAllByOrderByIdAsc();
}
