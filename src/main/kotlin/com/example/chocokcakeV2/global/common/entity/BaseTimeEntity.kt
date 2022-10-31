package com.example.chocokcakeV2.global.common.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity(
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime?
) {

    @CreatedDate
    @Column(name = "created_at")
    var createdAt: LocalDateTime = createdAt
        protected set

    @LastModifiedDate
    @Column(name = "update_at")
    var updatedAt: LocalDateTime? = updatedAt
        protected set
}