package com.example.chocokcakeV2.domain.auth.entity.user

import com.example.chocokcakeV2.domain.auth.entity.user.type.Role
import com.example.chocokcakeV2.global.common.entity.BaseTimeEntity
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "user")
@Where(clause = "is_deleted = false")
@SQLDelete(sql = "UPDATE `user` SET is_deleted = true WHERE id = ?")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type")
abstract class User(
    id: Long?,
    name: String,
    accountId: String,
    password: String,
    birthDay: LocalDate?,
    role: Role,
    createdAt: LocalDateTime,
    updatedAt: LocalDateTime?
) : BaseTimeEntity(createdAt, updatedAt), UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = id
        protected set

    @Column(name = "name", length = 60, nullable = false)
    var name: String = name
        protected set

    @Column(name = "account_id", length = 60, nullable = false)
    var accountId: String = accountId
        protected set

    @Column(name = "password", length = 60, nullable = false)
    private var password: String = password

    override fun getPassword(): String {
         return this.password
    }

    @Column(name = "is_deleted", nullable = false)
    var isDeleted: Boolean = false
        protected set

    @Column(name = "birth_day")
    var birthDay: LocalDate? = birthDay
        protected set

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    var role: Role = role
        protected set

    override fun getAuthorities(): MutableCollection<GrantedAuthority> {
        val authorities: MutableList<GrantedAuthority> = ArrayList()
        authorities.add(SimpleGrantedAuthority(this.role.toString()))
        return authorities
    }

    override fun getUsername(): String {
        return this.accountId
    }

    override fun isAccountNonExpired(): Boolean {
        return false
    }

    override fun isAccountNonLocked(): Boolean {
        return false
    }

    override fun isCredentialsNonExpired(): Boolean {
        return false
    }

    override fun isEnabled(): Boolean {
        return false
    }
}