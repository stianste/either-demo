package example.eitherdemo.ativity.suggestions.models.external.api

data class BoredApiResponse(
  val activity: String,
  val type: String,
  val participants: Int,
  val price: Double,
  val link: String,
  val key: String,
  val accessibility: Double,
)
