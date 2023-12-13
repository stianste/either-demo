package example.sealedclasses

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
    val errors: List<DomainException>,
  ) : AppException {
    override val message =
      if (errors.contains(DomainException.BasicallyExpected)) "Pjuh" else "OMG the world is burning"
  }
}

enum class DomainException {
  BasicallyExpected,
  TotalDisaster,
}
