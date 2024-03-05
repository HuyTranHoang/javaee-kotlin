package com.huy.ebookkotlin.controller.client

import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "LoginServlet", urlPatterns = ["/login"])
class LoginServlet : HttpServlet() {

    @Throws(ServletException::class, IOException::class)
    override fun doGet(request: HttpServletRequest, response: HttpServletResponse?) {
        request.getRequestDispatcher("/client/login.jsp").forward(request, response)
    }
}