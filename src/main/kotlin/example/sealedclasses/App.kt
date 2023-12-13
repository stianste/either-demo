package example.sealedclasses

class App {
  companion object {
    fun handleError(exception: AppException): Int =
      when (exception) {
        is AppException.ClientError -> {
          val wellDefinedStatus = exception.httpStatus!! // redundant now
          wellDefinedStatus
        }
        is AppException.ThatDomainExceptionWhichNeedsSpecialAttention -> {
          val errors = exception.errors
          println("Would do some special handling here")
          200
        }
      }
  }
}
