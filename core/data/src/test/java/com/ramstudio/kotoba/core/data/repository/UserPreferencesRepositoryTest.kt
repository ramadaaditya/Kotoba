package com.ramstudio.kotoba.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File

@OptIn(ExperimentalCoroutinesApi::class)
class UserPreferencesRepositoryTest {

    @get:Rule
    val temporaryFolder = TemporaryFolder()

    private lateinit var dataStore: DataStore<Preferences>
    private lateinit var repository: UserPreferencesRepository

    private val testScope = TestScope(UnconfinedTestDispatcher())

    @Before
    fun setup() {
        dataStore = PreferenceDataStoreFactory.create(
            scope = testScope,
            produceFile = { File(temporaryFolder.newFolder(), "user_prefs.preferences_pb") }
        )
        repository = UserPreferencesRepositoryImpl(dataStore)
    }

    @Test
    fun `isOnboardingCompleted is initially false`() = runTest {
        assertFalse(repository.isOnboardingCompleted.first())
    }

    @Test
    fun `setOnboardingCompleted updates value`() = runTest {
        repository.setOnboardingCompleted(true)
        assertTrue(repository.isOnboardingCompleted.first())
    }
}
