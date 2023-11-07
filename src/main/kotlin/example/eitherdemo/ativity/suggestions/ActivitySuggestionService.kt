package example.eitherdemo.ativity.suggestions

import arrow.core.Either
import arrow.core.mapOrAccumulate
import example.eitherdemo.ativity.suggestions.models.exceptions.AppError
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
}
