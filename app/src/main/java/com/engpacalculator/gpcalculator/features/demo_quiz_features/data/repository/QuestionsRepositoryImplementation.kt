package com.engpacalculator.gpcalculator.features.demo_quiz_features.data.repository

import android.os.Build
import androidx.annotation.RequiresExtension
import com.engpacalculator.gpcalculator.core.util.Resource
import com.engpacalculator.gpcalculator.features.demo_quiz_features.data.remote.QuestionApi
import com.engpacalculator.gpcalculator.features.demo_quiz_features.domain.model.QuestionDetails
import com.engpacalculator.gpcalculator.features.demo_quiz_features.domain.repository.QuestionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class QuestionsRepositoryImplementation(
    private val api: QuestionApi
) : QuestionRepository {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun getQuestions(category: String): Flow<Resource<QuestionDetails>> = flow {
        emit(Resource.Loading())
        try {
            val results = api.getQuestions(category = category)
            emit(Resource.Success(data = results.toQuestionDetails()))

        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server an error occurred",
                    data = null
                )
            )

        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!!!",
                    data = null
                )
            )


        }

    }
}

