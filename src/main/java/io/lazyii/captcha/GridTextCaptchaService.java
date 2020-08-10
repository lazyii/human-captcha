package io.lazyii.captcha;

import java.time.LocalDateTime;

/**
 * @author wyd
 * @time 2020/8/8 10:27 下午
 * @description
 */
public class GridTextCaptchaService implements CaptchaService{

    public String sayHi() {
        return "hi " + LocalDateTime.now();
    }
}
