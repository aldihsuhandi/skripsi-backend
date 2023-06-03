package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDO, String> {
    @Modifying
    @Transactional
    @Query("UPDATE UserDO u SET u.profilePicture = :profile_picture WHERE u.userId = :user_id")
    void updateProfilePicture(@Param("user_id") String userId, @Param("profile_picture") String profilePicture);

    @Modifying
    @Transactional
    @Query("UPDATE UserDO u SET u.roleId = :role_id WHERE u.userId = :user_id")
    void changeRoleUser(@Param("user_id") String userId, @Param("role_id") String roleId);

    @Modifying
    @Transactional
    @Query("UPDATE UserDO u SET u.review = :review WHERE u.userId = :id")
    void updateUserReview(@Param("id") String userId, @Param("review") double review);

    @Query("SELECT u FROM UserDO u WHERE u.userId IN :user_ids")
    List<UserDO> findByIds(@Param("user_ids") List<String> userIds);

    Optional<UserDO> findByEmail(String email);

    Optional<UserDO> findByPhoneNumber(String phoneNumber);

    Optional<UserDO> findByUsername(String username);
}
