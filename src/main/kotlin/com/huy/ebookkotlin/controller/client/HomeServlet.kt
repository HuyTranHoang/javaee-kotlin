package com.huy.ebookkotlin.controller.client

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "HomeServlet", urlPatterns = ["/index", "/home"])
class HomeServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        req.setAttribute("contextPath", req.contextPath)
        req.getRequestDispatcher("/client/home.jsp").forward(req, resp)
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        super.doPost(req, resp)
    }
}