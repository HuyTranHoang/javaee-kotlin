package com.huy.ebookkotlin.controller.admin

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "AdminHomeServlet", urlPatterns = ["/admin"])
class AdminHomeServlet : HttpServlet(){

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        val dispatcher = req.getRequestDispatcher("/admin/index.jsp")
        dispatcher.forward(req, resp)
    }

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        super.doPost(req, resp)
    }
}