package com.melatech.chirp10

import com.melatech.chirp10.infra.database.entities.UserEntity
import com.melatech.chirp10.infra.database.repositories.UserRepository
import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.Component

@SpringBootApplication
@EnableScheduling
class Chirp10Application

fun main(args: Array<String>) {
	//println(ClassLoader.getSystemResource("email_rate_limit.lua"))

	runApplication<Chirp10Application>(*args)
}

//@Component
//class Demo(
//	private val repository: UserRepository
//){
//	@PostConstruct
//	fun init() {
//		repository.save(
//			UserEntity(
//				email = "test10@test.com",
//				username = "test10",
//				hashedPassword = "123"
//			)
//		)
//	}
//}
