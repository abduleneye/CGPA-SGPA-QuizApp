package com.engpacalculator.gpcalculator.core.di.uni_5

import android.app.Application
import androidx.room.Room
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.Converters
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.UniFiveSgpaResultRecordDB
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.repository.UniFiveSgpaResultRepositoryImplementation
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.util.GsonParser
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.domain.repository.UniFiveSgpaResultRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object uni_five_sgpa_module {


    @Provides
    @Singleton
    fun providesUniFiveSgpaResultRepository(
        db: UniFiveSgpaResultRecordDB
    ): UniFiveSgpaResultRepository {
        return UniFiveSgpaResultRepositoryImplementation(db.dao)

    }


    @Provides
    @Singleton
    fun providesUniFiveResultsRecordDB(app: Application): UniFiveSgpaResultRecordDB {

        return Room.databaseBuilder(
            context = app,
            klass = UniFiveSgpaResultRecordDB::class.java,
            name = "uni_five_sgpa_result_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }


}