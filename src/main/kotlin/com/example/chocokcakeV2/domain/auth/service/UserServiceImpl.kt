package com.example.chocokcakeV2.domain.auth.service

import com.example.chocokcakeV2.domain.auth.entity.user.Admin
import com.example.chocokcakeV2.domain.auth.entity.user.General
import com.example.chocokcakeV2.domain.auth.entity.user.User
import com.example.chocokcakeV2.domain.auth.presentation.dto.response.UserMaximumInfoResponse
import com.example.chocokcakeV2.domain.auth.repository.UserRepository
import com.example.chocokcakeV2.global.error.exception.NoPermissionsException
import com.example.chocokcakeV2.global.error.exception.UserNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository<User>
): UserService {
    override fun getUserInfo(user: User, id: Long): UserMaximumInfoResponse {
        if((user is Admin) || (user is General && user.id == id)){
            val u = userRepository.findByIdOrNull(id)
                ?: throw UserNotFoundException("user not found to id : $id")
            return UserMaximumInfoResponse(
                u.id!!,
                u.name,
                u.accountId,
                u.birthDay?.month?.value,
                u.birthDay?.dayOfMonth
            )
        }
        throw NoPermissionsException("No Permission : " + user.role)
    }
}