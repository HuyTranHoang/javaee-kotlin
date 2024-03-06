package com.huy.ebookkotlin.dao

import com.huy.ebookkotlin.entity.User

class UserDAO : JpaDAO<User>(User::class.java) {
    fun findByEmail(email: String): User? {
        var user: User? = null

        try {
            sessionFactory.openSession().use { session ->
                val builder = session.criteriaBuilder
                val criteria = builder.createQuery(User::class.java)
                val root = criteria.from(User::class.java)

                criteria.select(root)
                    .where(builder.equal(root.get<String>("email"), email))

                user = session.createQuery(criteria).singleResult
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return user
    }

    fun findByEmailWithHQL(email: String): User? {
        var user: User? = null

        try {
            sessionFactory.openSession().use { session ->
                val query = session.createNamedQuery("User.findByEmail", User::class.java)
                query.setParameter("email", email)

                user = query.singleResult
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return user
    }
}
