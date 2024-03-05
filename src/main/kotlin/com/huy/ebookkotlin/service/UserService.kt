package com.huy.ebookkotlin.service

import com.huy.ebookkotlin.dao.UserDAO
import com.huy.ebookkotlin.entity.User


class UserService {
    private val userDAO: UserDAO = UserDAO()

    fun getAllUsers() : List<User> {
        return userDAO.findAll()
    }

    fun getUserById(id: Int) : User? {
        return userDAO.find(id)
    }

    fun getUserByEmail(email: String) : User? {
        return userDAO.findByEmail(email)
    }

    fun createUser(user: User) {
        userDAO.create(user)
    }

    fun updateUser(user: User) {
        userDAO.update(user)
    }

    fun deleteUser(id: Int) {
        userDAO.delete(id)
    }

}
