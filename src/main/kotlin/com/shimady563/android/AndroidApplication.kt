package com.shimady563.android

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AndroidApplication

fun main(args: Array<String>) {
	runApplication<AndroidApplication>(*args)
}
