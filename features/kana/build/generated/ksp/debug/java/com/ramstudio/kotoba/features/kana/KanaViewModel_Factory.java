package com.ramstudio.kotoba.features.kana;

import com.ramstudio.kotoba.core.data.repository.KanaRepository;
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
public final class KanaViewModel_Factory implements Factory<KanaViewModel> {
  private final Provider<KanaRepository> kanaRepositoryProvider;

  private KanaViewModel_Factory(Provider<KanaRepository> kanaRepositoryProvider) {
    this.kanaRepositoryProvider = kanaRepositoryProvider;
  }

  @Override
  public KanaViewModel get() {
    return newInstance(kanaRepositoryProvider.get());
  }

  public static KanaViewModel_Factory create(Provider<KanaRepository> kanaRepositoryProvider) {
    return new KanaViewModel_Factory(kanaRepositoryProvider);
  }

  public static KanaViewModel newInstance(KanaRepository kanaRepository) {
    return new KanaViewModel(kanaRepository);
  }
}
