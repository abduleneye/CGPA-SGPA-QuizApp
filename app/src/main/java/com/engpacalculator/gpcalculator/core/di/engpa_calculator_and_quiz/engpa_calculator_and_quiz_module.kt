package com.engpacalculator.gpcalculator.core.di.engpa_calculator_and_quiz

import android.app.Application
import androidx.room.Room
import com.engpacalculator.gpcalculator.core.local_data_base.EnGpaCalculatorAndQuizLocalDataBase
import com.engpacalculator.gpcalculator.features.Four_grading_system_sgpa_features.data.repository.FourSgpaResultRepositoryImplementation
import com.engpacalculator.gpcalculator.features.Four_grading_system_sgpa_features.domain.repository.FourSgpaResultRepository
import com.engpacalculator.gpcalculator.features.demo_quiz_features.data.QuestionsRepositoryImplementation
import com.engpacalculator.gpcalculator.features.demo_quiz_features.data.remote.QuestionApi
import com.engpacalculator.gpcalculator.features.demo_quiz_features.domain.repository.QuestionRepository
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.five_cgpa_util.FiveCgpaGsonParserFiveCgpa
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.local.FiveCgpaDBFieldConverter
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.data.repository.FiveCgpaResultRepositoryImplementation
import com.engpacalculator.gpcalculator.features.five_grading_system_cgpa_features.domain.repository.FiveCgpaResultRepository
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.five_sgpa_util.FiveSgpaGsonParserFiveSgpa
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.five_sgpa_util.FourSgpaGsonParserFourSgpa
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.FiveSgpaDBFieldConverter
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.local.FourSgpaDBFieldConverter
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.data.repository.FiveSgpaResultRepositoryImplementation
import com.engpacalculator.gpcalculator.features.five_grading_system_sgpa_features.domain.repository.FiveSgpaResultRepository
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.data.repository.FourCgpaResultRepositoryImplementation
import com.engpacalculator.gpcalculator.features.four_grading_system_cgpa_features.domain.repository.FourCgpaResultRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object engpa_calculator_and_quiz_module {


    @Provides
    @Singleton
    fun providesFiveSgpaResultRepository(
        sgpaDb: EnGpaCalculatorAndQuizLocalDataBase
    ): FiveSgpaResultRepository {
        return FiveSgpaResultRepositoryImplementation(sgpaDb.fiveSgpaDao)

    }

    @Provides
    @Singleton
    fun providesFourSgpaResultRepository(
        sgpaDb: EnGpaCalculatorAndQuizLocalDataBase
    ): FourSgpaResultRepository {
        return FourSgpaResultRepositoryImplementation(sgpaDb.fourSgpaDao)

    }


    @Provides
    @Singleton
    fun providesGpaResultsRecordsAndQuizDB(app: Application): EnGpaCalculatorAndQuizLocalDataBase {

        return Room.databaseBuilder(
            context = app,
            klass = EnGpaCalculatorAndQuizLocalDataBase::class.java,
            name = "gpa_quiz_result_db"
        ).addTypeConverter(FiveSgpaDBFieldConverter(FiveSgpaGsonParserFiveSgpa(Gson())))
            .addTypeConverter(FiveCgpaDBFieldConverter(FiveCgpaGsonParserFiveCgpa(Gson())))
            .addTypeConverter(FourSgpaDBFieldConverter(FourSgpaGsonParserFourSgpa(Gson())))
            //.addTypeConverter(FiveCgpaDBFieldConverter(FiveCgpaGsonParserFiveCgpa(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun providesFiveCgpaResultRepository(
        cgpaDb: EnGpaCalculatorAndQuizLocalDataBase
    ): FiveCgpaResultRepository {
        return FiveCgpaResultRepositoryImplementation(cgpaDb.fiveCgpaDao)

    }

    @Provides
    @Singleton
    fun providesFourCgpaResultRepository(
        cgpaDb: EnGpaCalculatorAndQuizLocalDataBase
    ): FourCgpaResultRepository {
        return FourCgpaResultRepositoryImplementation(cgpaDb.fourCgpaDao)


    }

    @Provides
    @Singleton
    fun provideQuestionsRepository(
        api: QuestionApi
    ): QuestionRepository {
        return QuestionsRepositoryImplementation(api)
    }

    @Provides
    @Singleton
    fun providesQuestionsApi(): QuestionApi {
        return Retrofit.Builder()
            .baseUrl(QuestionApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuestionApi::class.java)
    }


}