package io.lazyii.captcha;

import io.lazyii.captcha.base.CaptchaConfig;
import io.lazyii.captcha.base.Coordinate;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by admin on 2020/8/11 16:28:03.
 */
class HumanCaptcheTest {

    @Test
    public void singleThreadTest() throws Exception {
        CaptchaConfig config = CaptchaConfig.getDEFAULT();
        config.setDebug(true);
        
        HumanCaptcha humanCaptche = HumanCaptcha.instance();
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Files.copy(Paths.get("d:/bg.png"), bos);
        byte[] aa = bos.toByteArray();
        
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            ByteArrayInputStream bis = new ByteArrayInputStream(aa);
            BufferedImage tmp = ImageIO.read(bis);
            Tuple3<List<Coordinate>, BufferedImage, char[]> tuple = humanCaptche.createImg(tmp);
            ImageIO.write(tuple._2, "png", new File("d:/img/"+UUID.randomUUID().toString() + ".png"));
            
            //生成加密信息
            float f1 = tuple._1.get(0).getX();
            float f2 = tuple._1.get(0).getY();
            float f3 = tuple._1.get(1).getX();
            float f4 = tuple._1.get(1).getY();
            long timestamp = System.currentTimeMillis();
            //cc7026a2-e699-44c9-be2e-84374d82d0e4|339.69235|154.92126|310.85727|92.20008|1597304193137
            String result = String.format("%s|%s|%s|%s|%s|%s", UUID.randomUUID().toString(), f1, f2, f3, f4, timestamp);
            System.out.println(result);
            System.out.println(new String(Base64.getEncoder().encode(result.getBytes())));
            System.out.println(new String(Base64.getDecoder().decode(Base64.getEncoder().encode(result.getBytes()))));
            System.out.println();
        }
        long t2 = System.currentTimeMillis();
        //约6.8min，410096ms
        System.out.println("耗时:" + (t2 - t1));
    }
    

    @Test
    public void multiThreadsTest() throws Exception {
        HumanCaptcha humanCaptche = HumanCaptcha.instance();
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Files.copy(Paths.get("d:/bg.png"), bos);
        byte[] aa = bos.toByteArray();
        
        long t1 = System.currentTimeMillis();
        List<CompletableFuture<Void>> futures = IntStream.range(0, 10000).mapToObj(x -> {
            ByteArrayInputStream bis = new ByteArrayInputStream(aa);
            return CompletableFuture.supplyAsync(() -> {
                try {
                    return ImageIO.read(bis);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }).thenApplyAsync((bufferedImage) -> humanCaptche.createImg(bufferedImage)).thenAcceptAsync((tuple) -> {
                try {
                    ImageIO.write(tuple._2, "png", new File("d:/img/" + UUID.randomUUID().toString() + ".png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }).collect(Collectors.toList());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();
        long t2 = System.currentTimeMillis();
        // 132786 ms
        System.out.println("耗时:" + (t2 - t1));
    }

}
