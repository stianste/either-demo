package example.sealedclasses

class App {
  companion object {
    fun handleError(exception: AppException): Int =
      when (exception) {
        is AppException.ThatFirstExternalApiException,
        is AppException.ThatSecondExternalApiException -> -1
        is AppException.ThatDomainExceptionWhichNeedsSpecialAttention -> {
          println("Would do some special handling here")
          200
        }
      }
  }
}
