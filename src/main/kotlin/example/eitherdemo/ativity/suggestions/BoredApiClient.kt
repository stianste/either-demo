package example.eitherdemo.ativity.suggestions

import example.eitherdemo.ativity.suggestions.models.external.api.BoredApiResponse
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class BoredApiClient {
  private val webClient = WebClient.create("https://www.boredapi.com/api/activity")

  fun fetchActivitySuggestion(activityType: String): BoredApiResponse? =
    webClient
      .get()
      .uri("?type=${activityType}")
      .retrieve()
      .toEntity(BoredApiResponse::class.java)
      .toFuture()
      .get()
      .body
}
