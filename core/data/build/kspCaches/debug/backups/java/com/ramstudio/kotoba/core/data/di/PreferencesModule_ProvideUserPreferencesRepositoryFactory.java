package com.ramstudio.kotoba.core.data.di;

import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import com.ramstudio.kotoba.core.data.repository.UserPreferencesRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class PreferencesModule_ProvideUserPreferencesRepositoryFactory implements Factory<UserPreferencesRepository> {
  private final Provider<DataStore<Preferences>> dataStoreProvider;

  private PreferencesModule_ProvideUserPreferencesRepositoryFactory(
      Provider<DataStore<Preferences>> dataStoreProvider) {
    this.dataStoreProvider = dataStoreProvider;
  }

  @Override
  public UserPreferencesRepository get() {
    return provideUserPreferencesRepository(dataStoreProvider.get());
  }

  public static PreferencesModule_ProvideUserPreferencesRepositoryFactory create(
      Provider<DataStore<Preferences>> dataStoreProvider) {
    return new PreferencesModule_ProvideUserPreferencesRepositoryFactory(dataStoreProvider);
  }

  public static UserPreferencesRepository provideUserPreferencesRepository(
      DataStore<Preferences> dataStore) {
    return Preconditions.checkNotNullFromProvides(PreferencesModule.INSTANCE.provideUserPreferencesRepository(dataStore));
  }
}
