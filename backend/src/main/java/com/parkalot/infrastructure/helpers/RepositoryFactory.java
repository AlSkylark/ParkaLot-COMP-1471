package com.parkalot.infrastructure.helpers;

import com.parkalot.api.BaseRepository;
import com.parkalot.infrastructure.models.BaseModel;
import java.lang.reflect.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class RepositoryFactory {

  @Autowired
  private ApplicationContext applicationContext;

  public <T extends BaseModel> BaseRepository<T> GetRepository(
    Type repositoryType
  ) {
    return (BaseRepository<T>) applicationContext.getBean(
      (Class<T>) repositoryType
    );
  }
}
