package com.huy.ebookkotlin.entity

import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "product")
class Product(
    var name: String = "",

    var author: String = "",

    var description: String = "",

    var isbn: String = "",

    var image: ByteArray = byteArrayOf(),

    var price: BigDecimal = BigDecimal.ZERO,

    @Column(name = "publish_date")
    private var publishDate: LocalDate? = null,

    @UpdateTimestamp
    @Column(name = "last_update_time")
    private var lastUpdateTime: LocalDate? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private var id: Int? = null,

    @ManyToOne
    @JoinColumn(name = "category_id")
    private var category: Category? = null
)