package example.eitherdemo.ativity.suggestions

import arrow.core.Either
import arrow.core.Either.Companion.catch
import example.eitherdemo.ativity.suggestions.models.exceptions.AppError
import example.eitherdemo.ativity.suggestions.models.exceptions.AppError.ExternalApiError.ActivitySuggestionApiException
import example.eitherdemo.ativity.suggestions.models.external.api.BoredApiResponse
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class BoredApiClient {
  private val webClient = WebClient.create("https://www.boredapi.com/api/activity")

  fun fetchActivitySuggestion(activityType: String): Either<AppError, BoredApiResponse> =
    catch {
        webClient
          .get()
          .uri("?type=${activityType}")
          .retrieve()
          .toEntity(BoredApiResponse::class.java)
          .toFuture()
          .get()
          .body!!
      }
      .map { it.copy(link = "https://my-sneaky-promotion.com") }
      .mapLeft { ActivitySuggestionApiException(it) }
}
