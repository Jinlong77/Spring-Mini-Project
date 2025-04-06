package org.kshrd.gamifiedhabittracker.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.gamifiedhabittracker.model.AppUserEntity;

import java.util.UUID;


@Mapper
public interface AppUserRepository {

    @Select("""
        SELECT * FROM app_users
        WHERE username = #{username}
    """)
    @Results(id = "appUserMapper", value = {
            @Result(property = "userId", column = "app_user_id", javaType = UUID.class),
            @Result(property = "profileImage", column = "profile_image"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createdAt", column = "created_at")
    })
    AppUserEntity findAppUserByUsername(String username);

    @Select("""
        SELECT * FROM app_users
        WHERE email = #{email}
    """)
    @ResultMap("appUserMapper")
    AppUserEntity findAppUserByEmail(String email);

    @Select("""
        INSERT INTO app_users
        VALUES(#{userId}, #{username}, #{email}, #{password}, #{level}, #{xp}, #{profileImage}, #{isVerified}, #{createdAt})
        RETURNING *
    """)
    @ResultMap("appUserMapper")
    AppUserEntity createAppUser(AppUserEntity appUserEntity);
}