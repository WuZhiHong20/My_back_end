package org.springblade.coach.login;

import org.springblade.coach.login.entity.pojo.UserDto;

/**
 * @author WZH
 * @version 1.0
 * @date 2022/5/10 17:21
 */
public class UserThreadLocal {
    private static final ThreadLocal<UserDto> LOCAL = new ThreadLocal<>();
    public static UserDto get(){return LOCAL.get();}
}
