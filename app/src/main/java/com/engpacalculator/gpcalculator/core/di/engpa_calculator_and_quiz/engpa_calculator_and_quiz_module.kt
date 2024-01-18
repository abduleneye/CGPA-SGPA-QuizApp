package com.engpacalculator.gpcalculator.core.di.engpa_calculator_and_quiz

import android.app.Application
import androidx.room.Room
import com.engpacalculator.gpcalculator.core.local_data_base.EnGpaCalculatorFiveSgpaLocalDataBase
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.five_sgpa_util.FiveSgpaGsonParserFiveSgpa
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.FiveSgpaDBFieldConverter
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.repository.FiveSgpaResultRepositoryImplementation
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.domain.repository.FiveSgpaResultRepository
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
        db: EnGpaCalculatorFiveSgpaLocalDataBase
    ): FiveSgpaResultRepository {
        return FiveSgpaResultRepositoryImplementation(db.dao)

    }


    @Provides
    @Singleton
    fun providesUniFiveResultsRecordDB(app: Application): EnGpaCalculatorFiveSgpaLocalDataBase {

        return Room.databaseBuilder(
            context = app,
            klass = EnGpaCalculatorFiveSgpaLocalDataBase::class.java,
            name = "uni_five_sgpa_result_db"
        ).addTypeConverter(FiveSgpaDBFieldConverter(FiveSgpaGsonParserFiveSgpa(Gson())))
            .build()
    }


}