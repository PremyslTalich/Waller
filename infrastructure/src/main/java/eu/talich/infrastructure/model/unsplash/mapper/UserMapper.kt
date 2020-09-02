package eu.talich.infrastructure.model.unsplash.mapper

import eu.talich.domain.model.User
import eu.talich.domain.model.mapper.Mapper
import eu.talich.infrastructure.model.unsplash.UserDto

class UserMapper:
    Mapper<UserDto, User> {
    override fun map(from: UserDto): User {
        return with(from) {
            User(
                id,
                username,
                name
            )
        }
    }
}