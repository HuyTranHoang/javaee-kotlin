package com.huy.ebookkotlin.filter

import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class RootRedirectFilter : Filter {
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        val request = servletRequest as HttpServletRequest
        val response = servletResponse as HttpServletResponse
        if (request.servletPath == "/") {
            response.sendRedirect(request.contextPath + "/index")
        } else {
            filterChain.doFilter(request, response)
        }
    }
}