package example.eitherdemo.ativity.suggestions

import example.eitherdemo.ativity.suggestions.models.external.api.BoredApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
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
  ): ResponseEntity<BoredApiResponse?> =
    ok().body(activitySuggestionService.getActivitySuggestion(activityType))
}
