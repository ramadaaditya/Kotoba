package com.ramstudio.kotoba.core.database.di;

import com.ramstudio.kotoba.core.database.KotobaDatabase;
import com.ramstudio.kotoba.core.database.dao.KanaDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class DatabaseModule_ProvideKanaDaoFactory implements Factory<KanaDao> {
  private final Provider<KotobaDatabase> databaseProvider;

  private DatabaseModule_ProvideKanaDaoFactory(Provider<KotobaDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public KanaDao get() {
    return provideKanaDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideKanaDaoFactory create(
      Provider<KotobaDatabase> databaseProvider) {
    return new DatabaseModule_ProvideKanaDaoFactory(databaseProvider);
  }

  public static KanaDao provideKanaDao(KotobaDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideKanaDao(database));
  }
}
