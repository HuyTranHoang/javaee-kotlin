package com.huy.ebookkotlin.entity

import javax.persistence.*

@Entity
@Table(name = "users")
@NamedQueries(
    NamedQuery(name = "User.findAll", query = "SELECT u FROM User u ORDER BY u.id DESC"),
    NamedQuery(name = "User.count", query = "SELECT COUNT(u) FROM User u"),
    NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
)

class User(
    var email: String = "",

    @Column(name = "full_name")
    var fullName: String = "",

    var password: String = "",

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var id: Int? = null
)