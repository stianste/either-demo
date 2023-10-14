package example.eitherdemo.words

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class WordController {

  @GetMapping("/word/definition/{word}")
  fun getWordDefinition(@PathVariable word: String) = "The word is: $word"
}
