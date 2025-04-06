package org.kshrd.gamifiedhabittracker.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.gamifiedhabittracker.model.AppUserEntity;
import org.kshrd.gamifiedhabittracker.model.dto.request.AppUserRequest;

@Mapper
public interface AppUserRepository {

    @Select("""
        SELECT * FROM app_users
        WHERE username = #{username}
    """)
    @Results(id = "appUserMapper", value = {
            @Result(property = "userId", column = "app_user_id"),
            @Result(property = "profileImage", column = "profile_image"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createdAt", column = "created_at")
    })
    AppUserEntity findAppUserByUsername(String username);

    @Select("""
        UPDATE app_users
        SET username = #{appUser.username}, profile_image = #{appUser.profileImage}
        WHERE email = #{email}
        RETURNING *
    """)
    @ResultMap("appUserMapper")
    AppUserEntity updateAppUserProfile(String email, @Param("appUser") AppUserRequest appUserRequest);

    @Select("""
        SELECT * FROM app_users
        WHERE email = #{email}
    """)
    @ResultMap("appUserMapper")
    AppUserEntity findAppUserByEmail(String email);

    @Select("""
        INSERT INTO app_users(username, email, password, level, xp, profile_image, is_verified, created_at)
        VALUES(#{username}, #{email}, #{password}, #{level}, #{xp}, #{profileImage}, #{isVerified}, #{createdAt})
        RETURNING *
    """)
    @ResultMap("appUserMapper")
    AppUserEntity createAppUser(AppUserEntity appUserEntity);

    @Select("""
        UPDATE app_users
        SET
            username = #{username},
            email = #{email},
            password = #{password},
            level = #{level},
            xp = #{xp},
            profile_image = #{profileImage},
            is_verified = #{isVerified}
        WHERE app_user_id = #{userId}
        RETURNING *
    """)
    @ResultMap("appUserMapper")
    AppUserEntity updateAppUser(AppUserEntity userEntity);

    @Delete("""
        DELETE FROM app_users
        WHERE app_user_id = #{userId}
    """)
    @ResultMap("appUserMapper")
    void delete(AppUserEntity userEntity);
}