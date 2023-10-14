package example.eitherdemo.ativity.suggestions

import org.springframework.stereotype.Service

@Service
class ActivitySuggestionService(private val apiClient: BoredApiClient) {
  fun getActivitySuggestion(activityType: String) = apiClient.fetchActivitySuggestion(activityType)
}
