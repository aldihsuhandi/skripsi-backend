/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.service.cache;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: UserFetchService.java, v 0.1 2022‐12‐28 8:57 AM Aldih Suhandi Exp $$
 */
@Service
public class UserFetchService {
    private final Map<String, UserVO> userCache = new HashMap<>();
    private final Map<String, String> emailMap = new HashMap<>();
    private final Map<String, String> phoneNumberMap = new HashMap<>();

    public void putToCache(UserVO userVO) {
        String userId = userVO.getUserId();
        String email = userVO.getEmail();
        String phoneNumber = userVO.getPhoneNumber();

        System.out.println("putting UserVO to cache");

        emailMap.put(email, userId);
        phoneNumberMap.put(phoneNumber, userId);
        userCache.put(userId, userVO);
    }

    public UserVO fetchFromCache(String value, String identifier) {
        System.out.println("fetching UserVO from cache");
        String userId = value;
        if (DatabaseConst.EMAIL.equals(identifier)) {
            userId = emailMap.get(value);
        } else if (DatabaseConst.PHONE_NUMBER.equals(identifier)) {
            userId = phoneNumberMap.get(value);
        }

        if (!userCache.containsKey(userId)) {
            return null;
        }

        return userCache.get(userId);
    }
}
