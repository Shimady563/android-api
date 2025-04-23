package com.shimady563.android

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<AndroidApplication>().with(TestcontainersConfiguration::class).run(*args)
}
