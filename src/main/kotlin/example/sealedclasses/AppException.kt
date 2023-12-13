package example.sealedclasses

import example.sealedclasses.DomainException.TotalDisaster

sealed interface AppException {
  val message: String

  sealed interface ClientError : AppException {
    val cause: Throwable
    val httpStatus: Int
  }

  data class ThatFirstExternalApiException(
    override val cause: Throwable,
    override val httpStatus: Int,
  ) : ClientError {
    override val message: String =
      cause.message
        ?: "Something went wrong when calling the first external api, and no message was provided."
  }

  data class ThatSecondExternalApiException(
    override val cause: Throwable,
    override val httpStatus: Int,
  ) : ClientError {
    override val message: String =
      cause.message
        ?: "Something went wrong when calling the second external api, and no message was provided."
  }

  data class ThatDomainExceptionWhichNeedsSpecialAttention(
    val domainCause: DomainException,
  ) : AppException {
    override val message = if (domainCause == TotalDisaster) "OMG the world is burning" else "Pjuh"
  }
}

enum class DomainException {
  BasicallyExpected,
  TotalDisaster,
}
