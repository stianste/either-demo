package example.eitherdemo.ativity.suggestions

import example.eitherdemo.ativity.suggestions.models.exceptions.AppError.ExternalApiError
import example.eitherdemo.ativity.suggestions.models.responses.ActivitySuggestionResponseDTO
import example.eitherdemo.ativity.suggestions.models.responses.ActivitySuggestionResponseDTO.ActivitySuggestionErrorResponse
import example.eitherdemo.ativity.suggestions.models.responses.ActivitySuggestionResponseDTO.ActivitySuggestionSuccessResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ActivitySuggestionController(
  private val activitySuggestionService: ActivitySuggestionService
) {

  @GetMapping("/activity-suggestion")
  fun getActivitySuggestion(
    @RequestParam activityType: String,
  ): ResponseEntity<ActivitySuggestionResponseDTO> =
    activitySuggestionService
      .getActivitySuggestion(activityType)
      .fold(
        {
          val statusCode =
            if (it is ExternalApiError) it.httpStatus else HttpStatus.INTERNAL_SERVER_ERROR
          status(statusCode).body(ActivitySuggestionErrorResponse(it.message))
        },
        { ok().body(ActivitySuggestionSuccessResponse(it)) }
      )
}
