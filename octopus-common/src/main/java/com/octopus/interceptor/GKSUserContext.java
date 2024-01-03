

package com.octopus.interceptor;

import cn.hutool.core.text.UnicodeUtil;
import com.octopus.dto.UserLoginInfoDTO;
import org.springframework.core.NamedInheritableThreadLocal;

public class GKSUserContext {
    private static final NamedInheritableThreadLocal<UserLoginInfoDTO> GKS_CURRENT_LOGIN_USER = new NamedInheritableThreadLocal("gksLoginUser");

    public GKSUserContext() {
    }

    public static Integer getShopId() {
        return getCurrentUser().getShopId();
    }

    public static Integer getCompanyId() {
        return getCurrentUser().getCompanyId();
    }

    public static Integer getUserId() {
        return getCurrentUser().getUserId();
    }

    public static String getUserName() {
        return UnicodeUtil.toString(getCurrentUser().getUserName());
    }

    public static UserLoginInfoDTO getCurrentUser() {
        boolean userLogin = isUserLogin();
        return userLogin ? (UserLoginInfoDTO)GKS_CURRENT_LOGIN_USER.get() : new UserLoginInfoDTO();
    }

    public static boolean isUserLogin() {
        return GKS_CURRENT_LOGIN_USER.get() != null;
    }

    public static void bindLoginUserInfo(UserLoginInfoDTO userLoginInfoDTO) {
        if (userLoginInfoDTO != null) {
            GKS_CURRENT_LOGIN_USER.set(userLoginInfoDTO);
        } else {
            GKS_CURRENT_LOGIN_USER.remove();
        }

    }

    public static void unbindLoginUserInfo() {
        GKS_CURRENT_LOGIN_USER.remove();
    }
}
