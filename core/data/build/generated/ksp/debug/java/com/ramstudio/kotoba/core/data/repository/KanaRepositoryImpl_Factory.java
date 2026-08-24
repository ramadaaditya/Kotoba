package com.ramstudio.kotoba.core.data.repository;

import com.ramstudio.kotoba.core.database.dao.KanaDao;
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
public final class KanaRepositoryImpl_Factory implements Factory<KanaRepositoryImpl> {
  private final Provider<KanaDao> kanaDaoProvider;

  private KanaRepositoryImpl_Factory(Provider<KanaDao> kanaDaoProvider) {
    this.kanaDaoProvider = kanaDaoProvider;
  }

  @Override
  public KanaRepositoryImpl get() {
    return newInstance(kanaDaoProvider.get());
  }

  public static KanaRepositoryImpl_Factory create(Provider<KanaDao> kanaDaoProvider) {
    return new KanaRepositoryImpl_Factory(kanaDaoProvider);
  }

  public static KanaRepositoryImpl newInstance(KanaDao kanaDao) {
    return new KanaRepositoryImpl(kanaDao);
  }
}
