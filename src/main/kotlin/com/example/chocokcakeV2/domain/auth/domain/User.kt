package com.example.chocokcakeV2.domain.auth.domain

import com.example.chocokcakeV2.domain.auth.domain.type.Role
import com.example.chocokcakeV2.global.common.entity.BaseTimeEntity
import org.hibernate.annotations.Where
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "user")
@Where(clause = "is_deleted = false")
@DiscriminatorColumn(name = "user_type")
abstract class User(
    id: Long?,
    name: String,
    accountId: String,
    password: String,
    role: Role,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime?
) : BaseTimeEntity(createdAt, updatedAt) {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = id
        protected set

    @Column(name = "name", length = 60, nullable = false)
    var name: String = name
        protected set

    @Column(name = "account_id", length = 60, nullable = false)
    var accountId: String = accountId
        protected set

    @Column(name = "password", length = 60, nullable = false)
    var password: String = password
        protected set

    @Column(name = "is_deleted", nullable = false)
    var isDeleted: Boolean = false
        protected set

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    var roleList: Role = role
        protected set

    fun editIsDelete(status: Boolean){
        this.isDeleted = status
    }
}