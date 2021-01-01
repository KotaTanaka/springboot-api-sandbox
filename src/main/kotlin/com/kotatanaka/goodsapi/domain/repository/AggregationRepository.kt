package com.kotatanaka.goodsapi.domain.repository

import com.kotatanaka.goodsapi.domain.entity.AggregationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/** [AggregationEntity] のリポジトリ */
@Repository
interface AggregationRepository : JpaRepository<AggregationEntity, Int>
