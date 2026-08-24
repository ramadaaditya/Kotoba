package com.ramstudio.kotoba.features.onboarding;

import com.ramstudio.kotoba.core.data.repository.UserPreferencesRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class OnboardingViewModel_Factory implements Factory<OnboardingViewModel> {
  private final Provider<UserPreferencesRepository> userPreferencesRepositoryProvider;

  private OnboardingViewModel_Factory(
      Provider<UserPreferencesRepository> userPreferencesRepositoryProvider) {
    this.userPreferencesRepositoryProvider = userPreferencesRepositoryProvider;
  }

  @Override
  public OnboardingViewModel get() {
    return newInstance(userPreferencesRepositoryProvider.get());
  }

  public static OnboardingViewModel_Factory create(
      Provider<UserPreferencesRepository> userPreferencesRepositoryProvider) {
    return new OnboardingViewModel_Factory(userPreferencesRepositoryProvider);
  }

  public static OnboardingViewModel newInstance(
      UserPreferencesRepository userPreferencesRepository) {
    return new OnboardingViewModel(userPreferencesRepository);
  }
}
