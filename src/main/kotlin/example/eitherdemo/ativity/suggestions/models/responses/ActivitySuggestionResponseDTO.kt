package example.eitherdemo.ativity.suggestions.models.responses

import example.eitherdemo.ativity.suggestions.models.external.api.BoredApiResponse

sealed interface ActivitySuggestionResponseDTO {
  data class ActivitySuggestionSuccessResponse(val activitySuggestion: BoredApiResponse) :
    ActivitySuggestionResponseDTO

  data class ActivitySuggestionErrorResponse(val message: String) : ActivitySuggestionResponseDTO
}
