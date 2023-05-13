package com.program.helper;

import com.program.model.User;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class TokenHelperImpl implements TokenHelper{

    HashMap<Integer, User> tokensUserMap = new HashMap<>();


    @Override
    public int generateTokenForUser(User user) {
        tokensUserMap.put(user.hashCode(), user);
        return user.hashCode();
    }

    @Override
    public void deleteTokenForUser(int token) {
        tokensUserMap.remove(token);
    }

    @Override
    public User getUserByToken(int token) {
        return tokensUserMap.get(token);
    }


}