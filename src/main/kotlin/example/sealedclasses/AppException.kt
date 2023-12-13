package example.sealedclasses

class AppException(
  val message: String,
  val cause: Throwable? = null,
  val httpStatus: Int? = null,
  val domainExceptionsWhichDontHaveAnythingToDoWithExternalApis: DomainException? = null,
) {
  class ThatFirstExternalApiException(
    cause: Throwable,
    httpStatus: Int,
  ) :
    AppException(
      message =
        cause.message
          ?: "Something went wrong when calling the first external api, and no message was provided.",
      cause = cause,
      httpStatus = httpStatus,
    )

  class ThatSecondExternalApiException(
    cause: Throwable,
    httpStatus: Int,
  ) :
    AppException(
      message =
        cause.message
          ?: "Something went wrong when calling the second external api, and no message was provided.",
      cause = cause,
      httpStatus = httpStatus,
    )

  class ThatDomainExceptionWhichNeedsSpecialAttention(
    domainCause: DomainException,
  ) :
    AppException(
      message =
        if (domainCause == DomainException.TotalDisaster) "OMG the world is burning" else "Pjuh",
      cause = null,
      httpStatus = if (domainCause == DomainException.BasicallyExpected) 200 else 500,
    )
}

enum class DomainException {
  BasicallyExpected,
  TotalDisaster,
}
