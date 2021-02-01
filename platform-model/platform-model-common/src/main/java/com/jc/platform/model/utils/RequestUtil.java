package com.jc.platform.model.utils;

import com.jc.platform.common.model.UserInfo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


/**
 * ClassName PlatformModelApplication
 * Description {this.desc!''}
 *
 * @author 平台管理员
 * @version 4.0
 * @date 2021/01/28
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestUtil {

    /**
     * Gets login user.
     *
     * @return the login user
     */
    public static UserInfo getLoginUser() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUserName("管理员");
        return userInfo;
    }
}
