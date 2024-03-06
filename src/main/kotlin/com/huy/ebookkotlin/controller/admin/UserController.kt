package com.huy.ebookkotlin.controller.admin

import com.huy.ebookkotlin.entity.User
import com.huy.ebookkotlin.service.UserService
import org.apache.commons.beanutils.BeanUtils
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "UserController", urlPatterns = ["/admin/users/*"])
class UserController : HttpServlet() {
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        val action = req.pathInfo ?: ""

        if (action.startsWith("/new")) {
            showCreateForm(req, resp)
        } else if (action.startsWith("/edit/")) {
            showEditForm(req, resp, action)
        }

        listUser(req, resp)
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        val action = req.pathInfo ?: ""

        when(action) {
            "/insert" -> createUser(req, resp)
            "/update" -> updateUser(req, resp)
            "/delete" -> deleteUser(req, resp)
            else -> {
                resp.sendRedirect(req.contextPath + "/admin/users")
            }
        }
    }

    private fun listUser(req: HttpServletRequest, resp: HttpServletResponse) {
        val userService = UserService()
        val users = userService.getAllUsers()
        req.setAttribute("users", users)

        val dispatcher = req.getRequestDispatcher("/admin/user/user_list.jsp")
        dispatcher.forward(req, resp)
    }

    private fun showCreateForm(req: HttpServletRequest, resp: HttpServletResponse) {
        val user = User()
        req.setAttribute("mode", "create")
        req.setAttribute("user", user)
        val dispatcher = req.getRequestDispatcher("/admin/user/user_form.jsp")
        dispatcher.forward(req, resp)
    }

    private fun showEditForm(req: HttpServletRequest, resp: HttpServletResponse, action: String) {
        val parts = action.split("/".toRegex())
        if (parts.size == 3) {
            val userIdString = parts[2]
            val userId = userIdString.toInt()

            val userService = UserService()
            val user = userService.getUserById(userId)

            if (user != null) {
                req.setAttribute("mode", "edit")
                req.setAttribute("user", user)
            }

            //TODO: handle else case

            val dispatcher = req.getRequestDispatcher("/admin/user/user_form.jsp")
            dispatcher.forward(req, resp)
        } else {
            resp.sendRedirect(req.contextPath + "/admin/users")
        }
    }

    private fun createUser(req: HttpServletRequest, resp: HttpServletResponse) {
        val user = User()
        try {
            BeanUtils.populate(user, req.parameterMap)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val userService = UserService()

        if(userService.getUserByEmail(user.email) != null) {
            req.session.setAttribute("error", "User with email ${user.email} already exists!")
            resp.sendRedirect(req.contextPath + "/admin/users/new")
            return
        }

        userService.createUser(user)

        req.session.setAttribute("message", "User has been created successfully!")

        resp.sendRedirect(req.contextPath + "/admin/users")
    }

    private fun updateUser(req: HttpServletRequest, resp: HttpServletResponse) {
        val user = User()
        try {
            BeanUtils.populate(user, req.parameterMap)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val userService = UserService()

        if (userService.getUserByEmail(user.email) != null && userService.getUserByEmail(user.email)?.id != user.id) {
            req.session.setAttribute("error", "User with email ${user.email} already exists!")
            resp.sendRedirect(req.contextPath + "/admin/users/edit/${user.id}")
            return
        }

        userService.updateUser(user)

        req.session.setAttribute("message", "User has been updated successfully!")

        resp.sendRedirect(req.contextPath + "/admin/users")
    }

    private fun deleteUser(req: HttpServletRequest, resp: HttpServletResponse) {
        val id = req.getParameter("deleteUserId").toInt()
        val userService = UserService()
        userService.deleteUser(id)

        req.session.setAttribute("message", "User has been deleted successfully!")

        resp.sendRedirect(req.contextPath + "/admin/users")
    }


}