package com.intelligentsia_backend.services.service;

import com.intelligentsia_backend.entity.PasswordResetToken;
import com.intelligentsia_backend.entity.Role;
import com.intelligentsia_backend.entity.User;
import com.intelligentsia_backend.entity.VerificationToken;
import com.intelligentsia_backend.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User registerUser(UserModel userModel);
    Role addNewRole(Role role);
    void addRoleToUser(String email,String roleName);
    List<User> listUsers();
    void saveVerificationTokenForUser(String token, User user);
    void saveVerificationTokenForSuper(String token, String email);
    String validateVerificationToken(String token);
    String validateVerificationTokenInstructorOrStaff(String token);

    User findUserByEmail(String email);

    VerificationToken generateNewVerificationToken(Long id);

    void createPasswordResetTokenForUser(User user, String token);

    String validatePasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changePassword(User user, String newPassword);

    boolean checkIfValidOldPassword(User user, String oldPassword);
    public PasswordResetToken generateNewPasswordResetToken(String token, String mail);
   // User connexion(String email, String password);

    PasswordResetToken findById(Long id);
}
