package com.kotatanaka.goodsapi.domain.entity

import org.hibernate.annotations.CreationTimestamp
import java.sql.Timestamp
import java.util.Date
import javax.persistence.Basic
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

/** 統計テーブルのエンティティ */
@Entity
@Table(name = "aggregation", schema = "goods_db")
data class AggregationEntity(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  var id: Int = 0,

  @Basic
  @Column(name = "request", nullable = false, length = 32)
  var request: String = "",

  @Basic
  @Column(name = "status_code", nullable = false)
  var statusCode: Int = 0,

  @Basic
  @Column(name = "access_times", nullable = false)
  var accessTimes: Int = 0,

  @Basic
  @Column(name = "average_time", nullable = false)
  var averageTime: Int = 0,

  @Basic
  @Column(name = "created_at", nullable = false)
  @CreationTimestamp
  var aggregatedAt: Timestamp = Timestamp(Date().time)
)
