package example.eitherdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication class EitherDemoApplication

fun main(args: Array<String>) {
  runApplication<EitherDemoApplication>(*args)
}
