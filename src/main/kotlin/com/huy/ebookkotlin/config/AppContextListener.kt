package com.huy.ebookkotlin.config

import com.huy.ebookkotlin.service.UserService
import javax.servlet.ServletContextEvent
import javax.servlet.ServletContextListener

class AppContextListener : ServletContextListener {
    override fun contextInitialized(sce: ServletContextEvent) {
        val servletContext = sce.servletContext
        val userService = UserService()
        servletContext.setAttribute("userService", userService)
    }

    override fun contextDestroyed(sce: ServletContextEvent?) {
        // clean up resources
    }
}