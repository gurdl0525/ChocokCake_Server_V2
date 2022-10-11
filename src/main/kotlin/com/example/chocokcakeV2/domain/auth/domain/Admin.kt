package com.example.chocokcakeV2.domain.auth.domain

import com.example.chocokcakeV2.domain.auth.domain.type.Role
import java.time.LocalDateTime
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue(value = "ADMIN")
class Admin(
    id: Long?,
    name: String,
    accountId: String,
    password: String,
    createdAt: LocalDateTime
): User(
    id = id,
    name = name,
    accountId = accountId,
    password = password,
    birthDay = null,
    role = Role.ADMIN,
    createdAt = createdAt,
    updatedAt = null
) {
}