package com.huy.ebookkotlin.config

import com.huy.ebookkotlin.entity.User
import org.hibernate.SessionFactory
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration
import org.hibernate.cfg.Environment
import org.hibernate.service.ServiceRegistry
import java.util.*

object HibernateSessionFactoryConfig {
    var sessionFactory: SessionFactory? = null
        get() {
            if (field == null) {
                try {
                    val prop = properties

                    val configuration = Configuration()
                    configuration.setProperties(prop)

                    // Models..
                    configuration.addAnnotatedClass(User::class.java)
//                    configuration.addAnnotatedClass(Category::class.java)
//                    configuration.addAnnotatedClass(Product::class.java)
//                    configuration.addAnnotatedClass(Customer::class.java)

                    val serviceRegistry: ServiceRegistry = StandardServiceRegistryBuilder()
                        .applySettings(configuration.properties)
                        .build()

                    field = configuration.buildSessionFactory(serviceRegistry)

                    return field
                } catch (ex: Throwable) {
                    System.err.println("Initial SessionFactory creation failed.$ex")
                    throw ExceptionInInitializerError(ex)
                }
            }

            return field
        }
        private set

    private val properties: Properties
        get() {
            val prop = Properties()
            prop.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/c2202l_javaee_ebook?useSSL=false")
            prop.setProperty(Environment.USER, "root")
            prop.setProperty(Environment.PASS, "g9v$;6~CmQRCq#U")
            prop.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver")
            prop.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect")
            prop.setProperty(Environment.SHOW_SQL, "true")
            prop.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread")
            prop.setProperty(Environment.HBM2DDL_AUTO, "update")
            return prop
        }
}
