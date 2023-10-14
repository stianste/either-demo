package example.eitherdemo.ativity.suggestions

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
  ) = activitySuggestionService.getActivitySuggestion(activityType)
}
