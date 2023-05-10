package com.program.helper;

import com.program.model.Teacher;
import com.program.model.User;

public interface TokenHelper {

    int generateTokenForUser(User user);

    void deleteTokenForUser(int token);

    User getUserByToken(int token);

    int generateTokenForTeacher(Teacher teacher);

    void deleteTokenForTeacher(int token);

    Teacher getTeacherByToken(int token);
}
