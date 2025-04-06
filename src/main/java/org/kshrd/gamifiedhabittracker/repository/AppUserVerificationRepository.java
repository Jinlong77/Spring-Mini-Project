package org.kshrd.gamifiedhabittracker.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.gamifiedhabittracker.model.AppUserVerificationEntity;

import java.util.UUID;

@Mapper
public interface AppUserVerificationRepository {


    @Select("""
        SELECT * FROM user_verification
        WHERE app_user_id = #{userId}
    """)
    @Results(id = "verificationMapper", value = {
            @Result(property = "verificationId", column = "verification_id", javaType = UUID.class),
            @Result(property = "userId", column = "app_user_id", javaType = UUID.class),
            @Result(property = "expirationTime", column = "expiration_time"),
            @Result(property = "createdAt", column = "created_at")
    })
    AppUserVerificationEntity findByAppUserId(UUID userId);

    @Select("""
        SELECT * FROM user_verification
        WHERE otp = #{otp}
    """)
    @ResultMap("verificationMapper")
    AppUserVerificationEntity findByOtp(String otp);

    @Insert("""
        INSERT INTO user_verification(app_user_id, otp, expiration_time, created_at)
        VALUES (#{v.userId}, #{v.otp}, #{v.expirationTime}, #{v.createdAt})
    """)
    @ResultMap("verificationMapper")
    void save(@Param("v") AppUserVerificationEntity appUserVerificationEntity);

    @Delete("""
        DELETE FROM user_verification
        WHERE app_user_id = #{userId}
    """)
    void deleteByAppUserId(UUID userId);
}
