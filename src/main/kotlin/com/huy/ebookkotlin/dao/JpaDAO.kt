package com.huy.ebookkotlin.dao

import com.huy.ebookkotlin.config.HibernateSessionFactoryConfig
import io.github.oshai.kotlinlogging.KotlinLogging
import org.hibernate.SessionFactory
import org.hibernate.Transaction
private val logger = KotlinLogging.logger {}

open class JpaDAO<T>(type: Class<T>) {

    protected var sessionFactory: SessionFactory
    private var type: Class<T>

    init {
        this.type = type
        sessionFactory = HibernateSessionFactoryConfig.sessionFactory!!
    }

    open fun create(t: T): T {
        var transaction: Transaction? = null
        try {
            sessionFactory.openSession().use { session ->
                transaction = session.beginTransaction()
                session.save(t)
                transaction?.commit()
            }
        } catch (e: Exception) {
            transaction?.rollback()
            logger.error(e) { "Error creating entity" }
            e.printStackTrace()
        }

        return t
    }

    open fun update(t: T): T {
        var transaction: Transaction? = null
        try {
            sessionFactory.openSession().use { session ->
                transaction = session.beginTransaction()
                session.update(t)
                transaction?.commit()
            }
        } catch (e: Exception) {
            transaction?.rollback()
            logger.error(e) { "Error updating entity" }
            e.printStackTrace()
        }

        return t
    }

    open fun find(id: Int): T? {
        var t: T? = null
        try {
            sessionFactory.openSession().use { session ->
                t = session.get(this.type, id)
            }
        } catch (e: Exception) {
            logger.error(e) { "Error finding entity" }
            e.printStackTrace()
        }

        return t
    }

    open fun delete(id: Int) {
        var transaction: Transaction? = null
        try {
            sessionFactory.openSession().use { session ->
                transaction = session.beginTransaction()
                val t = session.get(this.type, id)
                session.delete(t)
                transaction?.commit()
            }
        } catch (e: Exception) {
            transaction?.rollback()
            logger.error(e) { "Error deleting entity" }
            e.printStackTrace()
        }
    }

    open fun findAll(): List<T> {
        var list: List<T> = emptyList()
        try {
            sessionFactory.openSession().use { session ->
                val builder = session.criteriaBuilder
                val criteria = builder.createQuery(this.type)
                val root = criteria.from(this.type)
                criteria.select(root)

                list = session.createQuery(criteria).resultList
            }
        } catch (e: Exception) {
            logger.error(e) { "Error finding all entities" }
            e.printStackTrace()
        }

        return list
    }

    open fun count(): Long {
        var count: Long = 0
        try {
            sessionFactory.openSession().use { session ->
                val builder = session.criteriaBuilder
                val criteria = builder.createQuery(Long::class.java)
                val root = criteria.from(this.type)
                criteria.select(builder.count(root))

                count = session.createQuery(criteria).singleResult
            }
        } catch (e: Exception) {
            logger.error(e) { "Error counting entities" }
            e.printStackTrace()
        }

        return count
    }
}