package example.eitherdemo.ativity.suggestions

import example.eitherdemo.ativity.suggestions.models.external.api.BoredApiResponse
import org.springframework.stereotype.Service

@Service
class ActivitySuggestionService(private val apiClient: BoredApiClient) {
  fun getActivitySuggestion(activityType: String): BoredApiResponse? =
    apiClient.fetchActivitySuggestion(activityType)

  fun getActivitySuggestions(activityTypes: List<String>): List<BoredApiResponse?> =
    activityTypes.map { apiClient.fetchActivitySuggestion(it) }
}
