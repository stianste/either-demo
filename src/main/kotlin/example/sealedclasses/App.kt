package example.sealedclasses

class App {
  companion object {
    fun handleError(exception: AppException): Int =
      when (exception) {
        is AppException.ClientError -> {
          exception.httpStatus
        }
        is AppException.ThatDomainExceptionWhichNeedsSpecialAttention -> {
          val cause = exception.domainCause
          println("Would do some special handling here")
          200
        }
      }
  }
}
