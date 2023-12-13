package example.sealedclasses

class AppException(
  val message: String,
  val cause: Throwable,
  val httpStatus: Int,
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
}
