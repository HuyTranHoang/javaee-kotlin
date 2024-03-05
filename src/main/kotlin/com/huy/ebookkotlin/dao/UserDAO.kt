package com.huy.ebookkotlin.dao

import com.huy.ebookkotlin.entity.User
import io.github.oshai.kotlinlogging.KotlinLogging


private val logger = KotlinLogging.logger {}


class UserDAO : JpaDAO<User>(User::class.java) {
    fun findByEmail(email: String): User? {
        var user: User? = null

        try {
            val query = sessionFactory.openSession().use { session ->
                val builder = session.criteriaBuilder
                val criteria = builder.createQuery(User::class.java)
                val root = criteria.from(User::class.java)

                criteria.select(root)
                    .where(builder.equal(root.get<String>("email"), email))

                user = session.createQuery(criteria).singleResult
            }
        } catch (e: Exception) {
            logger.error(e) { "Error finding user by email" }
            e.printStackTrace()
        }

        return user
    }
}
