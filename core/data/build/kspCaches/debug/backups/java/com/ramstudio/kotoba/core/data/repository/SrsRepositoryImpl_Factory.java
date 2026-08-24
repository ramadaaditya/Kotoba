package com.ramstudio.kotoba.core.data.repository;

import com.ramstudio.kotoba.core.database.dao.SrsDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class SrsRepositoryImpl_Factory implements Factory<SrsRepositoryImpl> {
  private final Provider<SrsDao> srsDaoProvider;

  private SrsRepositoryImpl_Factory(Provider<SrsDao> srsDaoProvider) {
    this.srsDaoProvider = srsDaoProvider;
  }

  @Override
  public SrsRepositoryImpl get() {
    return newInstance(srsDaoProvider.get());
  }

  public static SrsRepositoryImpl_Factory create(Provider<SrsDao> srsDaoProvider) {
    return new SrsRepositoryImpl_Factory(srsDaoProvider);
  }

  public static SrsRepositoryImpl newInstance(SrsDao srsDao) {
    return new SrsRepositoryImpl(srsDao);
  }
}
