package com.program.helper;

import com.program.model.User;

public interface TokenHelper {

    int generateToken(User user);

    void deleteToken(int token);

    User getUserByToken(int token);
}
