package com.kotatanaka.goodsapi.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import java.util.Date
import javax.persistence.Basic
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/** ユーザーテーブルのエンティティ */
@Entity
@Table(name = "user", schema = "goods_db")
data class UserEntity(
  @Id
  @Column(name = "id", nullable = false, length = 16)
  var id: String = "",

  @Basic
  @Column(name = "name", nullable = false, length = 10)
  var name: String = "",

  @Basic
  @Column(name = "password", nullable = false, length = 8)
  var password: String = "",

  @Basic
  @Column(name = "login_token", nullable = true, length = 32)
  var loginToken: String? = null,

  @Basic
  @Column(name = "created_at", nullable = false)
  @CreationTimestamp
  var createdAt: Timestamp = Timestamp(Date().time),

  @Basic
  @Column(name = "updated_at", nullable = false)
  @UpdateTimestamp
  var updatedAt: Timestamp = Timestamp(Date().time)
)
