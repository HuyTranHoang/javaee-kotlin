package com.huy.ebookkotlin.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "customer")
class Customer(
    var email: String = "",

    var fullName: String = "",

    var address: String = "",

    var city: String = "",

    var country: String = "",

    var phone: String = "",

    var zipCode: String = "",

    var password: String = "",

    @Column(name = "register_date")
    private var registerDate: LocalDate? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "customer_id")
    private var customerId: Int? = null
)