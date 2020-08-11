package io.lazyii.captcha;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by admin on 2020/8/11 16:28:03.
 */
class HumanCaptcheTest {

    @Test
    public void serviceLoaderTest() {
        HumanCaptche humanCaptche = HumanCaptche.instance();
        humanCaptche.printServiceClass();
        
    }

}
