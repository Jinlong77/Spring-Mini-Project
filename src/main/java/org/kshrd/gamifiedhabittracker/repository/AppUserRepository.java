package org.kshrd.gamifiedhabittracker.repository;
import org.apache.ibatis.annotations.*;
import org.kshrd.gamifiedhabittracker.model.dto.request.AppUserRequest;
import org.kshrd.gamifiedhabittracker.model.dto.response.AppUserDTO;

@Mapper
public interface AppUserRepository {
    @Results(id = "appUseMapper", value = {
            @Result(property = "userId", column = "app_user_id"),
            @Result(property = "profileImage", column = "profile_image"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "createAt", column = "created_at")
    })
    @Select(
      """
         SELECT * FROM app_users where email = #{email};
      """
    )
    AppUserDTO findAppUser(String email);

    @Select("""
        UPDATE app_users 
        SET username = #{appUser.username}, profile_image = #{appUser.profileImage}
        WHERE email = #{email}
        RETURNING *
    """)
    @ResultMap("appUseMapper")
    AppUserDTO updateAppUser(String email, @Param("appUser") AppUserRequest appUserRequest);

    @Delete(
    """
          delete  FROM app_users 
          WHERE email = #{email}
    """
    )
    void deleteAppUser(String email);
}
