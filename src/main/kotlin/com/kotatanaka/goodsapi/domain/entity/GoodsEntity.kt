package com.kotatanaka.goodsapi.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import java.util.Date
import javax.persistence.Basic
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

/** 商品テーブルのエンティティ */
@Entity
@Table(name = "goods", schema = "goods_db")
data class GoodsEntity(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  var id: Int = 0,

  @Basic
  @Column(name = "name", nullable = false, length = 50)
  var name: String = "",

  @Basic
  @Column(name = "description", nullable = true, length = 500)
  var description: String? = null,

  @Basic
  @Column(name = "price", nullable = false)
  var price: Int = 0,

  @Basic
  @Column(name = "created_at", nullable = false)
  @CreationTimestamp
  var createdAt: Timestamp = Timestamp(Date().time),

  @Basic
  @Column(name = "updated_at", nullable = false)
  @UpdateTimestamp
  var updatedAt: Timestamp = Timestamp(Date().time)
)
