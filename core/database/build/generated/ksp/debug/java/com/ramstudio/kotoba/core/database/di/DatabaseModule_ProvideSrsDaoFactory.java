package com.ramstudio.kotoba.core.database.di;

import com.ramstudio.kotoba.core.database.KotobaDatabase;
import com.ramstudio.kotoba.core.database.dao.SrsDao;
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
public final class DatabaseModule_ProvideSrsDaoFactory implements Factory<SrsDao> {
  private final Provider<KotobaDatabase> databaseProvider;

  private DatabaseModule_ProvideSrsDaoFactory(Provider<KotobaDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public SrsDao get() {
    return provideSrsDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideSrsDaoFactory create(
      Provider<KotobaDatabase> databaseProvider) {
    return new DatabaseModule_ProvideSrsDaoFactory(databaseProvider);
  }

  public static SrsDao provideSrsDao(KotobaDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideSrsDao(database));
  }
}
