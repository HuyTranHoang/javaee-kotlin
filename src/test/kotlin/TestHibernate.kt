import com.huy.ebookkotlin.config.HibernateSessionFactoryConfig
import com.huy.ebookkotlin.entity.User
import org.hibernate.Session
import org.hibernate.Transaction

fun main() {
    val sessionFactory = HibernateSessionFactoryConfig.sessionFactory
        ?: throw IllegalStateException("Hibernate session factory not available")

    try {
        val session: Session = sessionFactory.openSession()
        println("Connected to the database")

        session.use {
            val transaction: Transaction = session.beginTransaction()

            val user = User()
            user.email = "kotlin@gmail.com"
            user.fullName = "Kotlin"
            user.password = "kotlin123"

            session.save(user)
            transaction.commit()

            println("User saved with Id: " + user.id)

            val getUser: User = session.get(User::class.java, user.id)
            println("User get: " + getUser.fullName)
            println("User password: " + getUser.password)
        }
    } catch (e: Exception) {
        println("Oops, there's an error")
        e.printStackTrace()
    }
}
