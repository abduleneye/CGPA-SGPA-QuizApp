package com.engpacalculator.gpcalculator.core.data_store.data_store_repo

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val PREFERENCE_NAME = "my_preference"

class DataStoreRepo(private val context: Context) {

    //To make sure there is only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("QuizIntroDialogBoxVisibility")
        val DIALOG_BOX_VISIBILITY_KEY = booleanPreferencesKey("db_visibility")
        val DONT_SHOW_AGAIN_BUTTON_STATUS_KEY =
            booleanPreferencesKey("don't_show_again_button_status")

    }

    //To get the intro dialog box visibility status
    val getVisibilityStatus: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[DIALOG_BOX_VISIBILITY_KEY] ?: true

        }


    //To save the intro dialog box visibility status

    suspend fun saveVisibilityStatus(visibilityStatus: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DIALOG_BOX_VISIBILITY_KEY] = visibilityStatus

        }
    }

    //To get the button  status
    val getButtonStatus: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[DONT_SHOW_AGAIN_BUTTON_STATUS_KEY] ?: true

        }


    //To save the button  status

    suspend fun saveButtonStatus(visibilityStatus: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DONT_SHOW_AGAIN_BUTTON_STATUS_KEY] = visibilityStatus

        }
    }
}