package com.example.chocokcakeV2.domain.auth.entity.user

import com.example.chocokcakeV2.domain.auth.entity.user.type.Role
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDateTime
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue(value = "ADMIN")
@OnDelete(action = OnDeleteAction.CASCADE)
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