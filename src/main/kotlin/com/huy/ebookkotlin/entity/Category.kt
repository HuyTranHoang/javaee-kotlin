package com.huy.ebookkotlin.entity

import javax.persistence.*

@Entity
@Table(name = "category")
class Category(
    var name: String = "",

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    var id: Int? = null,

    @OneToMany(mappedBy = "category")
    private val products: List<Product>? = null
)