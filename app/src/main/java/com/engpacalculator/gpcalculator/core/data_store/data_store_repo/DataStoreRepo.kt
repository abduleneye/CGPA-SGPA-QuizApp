package com.engpacalculator.gpcalculator.core.data_store.data_store_repo

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuizIntroDataStoreRepoVisibility(private val context: Context) {

    //To make sure there is only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("QuizIntroDataStoreRepoVisibility")
        val QUIZ_INTRO_DIALOG_BOX_VISIBILITY_KEY = booleanPreferencesKey("quiz_intro_db_visibility")

    }


    //To get the intro dialog box visibility status
    val getQuizIntroDialogBoxVisibilityStatus: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[QUIZ_INTRO_DIALOG_BOX_VISIBILITY_KEY] ?: true

        }


    //To save the intro dialog box visibility status

    suspend fun saveQuizIntroDialogBoxVisibilityStatus(visibilityStatus: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[QUIZ_INTRO_DIALOG_BOX_VISIBILITY_KEY] = visibilityStatus

        }
    }

}

class FiveCgpaIntroDataStoreRepoVisibility(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("FiveCgpaIntroDataStoreRepoVisibility")
        val FIVE_CGPA_INTRO_DIALOG_BOX_VISIBILITY =
            booleanPreferencesKey("five_cgpa_intro_db_visibility")

    }

    //To get the five cgpa intro dialog box visibility status
    val getFiveCgpaIntroDialogBoxVisibilityStatus: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[FIVE_CGPA_INTRO_DIALOG_BOX_VISIBILITY]
                ?: true

        }


    //To save the five cgpa intro dialog box visibility status

    suspend fun saveFiveCgpaIntroDialogBoxVisibilityStatus(visibilityStatus: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[FIVE_CGPA_INTRO_DIALOG_BOX_VISIBILITY] =
                visibilityStatus

        }
    }

}

class FourCgpaIntroDataStoreRepoVisibility(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("FourCgpaIntroDataStoreRepoVisibility")
        val FOUR_CGPA_INTRO_DIALOG_BOX_VISIBILITY =
            booleanPreferencesKey("five_cgpa_intro_db_visibility")

    }

    //To get the five cgpa intro dialog box visibility status
    val getFourCgpaIntroDialogBoxVisibilityStatus: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[FOUR_CGPA_INTRO_DIALOG_BOX_VISIBILITY]
                ?: true

        }


    //To save the five cgpa intro dialog box visibility status

    suspend fun saveFourCgpaIntroDialogBoxVisibilityStatus(visibilityStatus: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[FOUR_CGPA_INTRO_DIALOG_BOX_VISIBILITY] =
                visibilityStatus

        }
    }
}


