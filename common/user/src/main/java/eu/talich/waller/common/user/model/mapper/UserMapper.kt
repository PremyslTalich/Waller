package eu.talich.waller.common.user.model.mapper

import eu.talich.waller.common.user.model.User
import eu.talich.waller.library.mapper.model.Mapper
import eu.talich.waller.library.unsplash.model.UserDto

class UserMapper: Mapper<UserDto, User> {
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