package example.eitherdemo.ativity.suggestions.models.exceptions

import org.springframework.http.HttpStatus

sealed interface AppError {
  val message: String

  sealed interface ExternalApiError : AppError {
    val cause: Throwable
    val httpStatus: HttpStatus

    class BoredApiException(
      override val cause: Throwable,
      override val httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR
    ) : ExternalApiError {
      override val message =
        cause.message
          ?: "Something went wrong when calling the activity suggestion api, and no message was provided."
    }
  }

  sealed class DomainException()
}
