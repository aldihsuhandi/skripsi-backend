/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.fetch;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.model.constant.DatabaseConst;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserFetchService.java, v 0.1 2022‐12‐28 8:57 AM Aldih Suhandi Exp $$
 */
@Service
public class UserFetchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogConstant.SERVICE_LOGGER);

    private final Map<String, UserVO> userCache = new HashMap<>();
    private final Map<String, String> emailMap = new HashMap<>();
    private final Map<String, String> phoneNumberMap = new HashMap<>();
    private final Map<String, String> usernameMap = new HashMap<>();

    public void putToCache(UserVO userVO) {
        LogUtil.info(LOGGER, String.format("userFetchService#putToCache[userVO=%s]", userVO));
        if (userVO == null) {
            return;
        }

        String userId = userVO.getUserId();
        String email = userVO.getEmail();
        String phoneNumber = userVO.getPhoneNumber();
        String username = userVO.getUsername();

        emailMap.put(email, userId);
        phoneNumberMap.put(phoneNumber, userId);
        usernameMap.put(username, userId);
        userCache.put(userId, userVO);
    }

    public UserVO fetchFromCache(String value, String identifier) {
        LogUtil.info(LOGGER, String.format("userFetchService#fetchFromCache[value=%s,identifier=%s]", value, identifier));
        String userId = value;
        if (DatabaseConst.EMAIL.equals(identifier)) {
            userId = emailMap.get(value);
        } else if (DatabaseConst.PHONE_NUMBER.equals(identifier)) {
            userId = phoneNumberMap.get(value);
        } else if (DatabaseConst.USERNAME.equals(identifier)) {
            userId = usernameMap.get(value);
        }

        if (!userCache.containsKey(userId)) {
            return null;
        }

        UserVO user = userCache.get(userId);
        LogUtil.info(LOGGER, String.format("userFetchService#fetchFromCache[userVO=%s]", user));

        return user;
    }

    public void clearCache() {
        LogUtil.info(LOGGER, "userFetchService#clearCache");
        userCache.clear();
        emailMap.clear();
        phoneNumberMap.clear();
    }
}
