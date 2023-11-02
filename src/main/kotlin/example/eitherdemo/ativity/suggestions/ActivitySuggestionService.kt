package example.eitherdemo.ativity.suggestions

import arrow.core.Either
import arrow.core.mapOrAccumulate
import arrow.core.raise.either
import example.eitherdemo.ativity.suggestions.models.exceptions.AppError
import example.eitherdemo.ativity.suggestions.models.exceptions.AppError.ExternalApiError.ActivitySuggestionApiException
import example.eitherdemo.ativity.suggestions.models.external.api.BoredApiResponse
import org.springframework.stereotype.Service

@Service
class ActivitySuggestionService(private val apiClient: BoredApiClient) {
  fun getActivitySuggestion(activityType: String): Either<AppError, BoredApiResponse> =
    apiClient.fetchActivitySuggestion(activityType)

  fun getActivitySuggestions(
    activityTypes: List<String>
  ): Either<List<AppError>, List<BoredApiResponse>> =
    activityTypes.mapOrAccumulate { apiClient.fetchActivitySuggestion(it).bind() }

  fun functionWhichWillAlwaysGoLeft(): Either<AppError, BoredApiResponse> = either {
    raise(ActivitySuggestionApiException(RuntimeException()))
    apiClient.fetchActivitySuggestion("social").bind()
  }

  fun fetchActivitySuggestions(): Either<AppError, List<BoredApiResponse>> = either {
    listOf("music", "social").map {
      apiClient.fetchActivitySuggestion(it).bind()
      apiClient.fetchActivitySuggestion(it).bind()
    }
  }
}
