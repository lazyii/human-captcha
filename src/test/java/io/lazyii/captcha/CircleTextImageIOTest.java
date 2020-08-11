package io.lazyii.captcha;

import io.lazyii.captcha.base.Circle;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.GlyphVector;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2020/8/10 13:49:37.
 */
public class CircleTextImageIOTest {
    
    
    @Test
    public void performanceTest() {
        int m = 100_000;
        long t1 = System.currentTimeMillis();
        List<Integer> countList = new ArrayList<>();
        System.out.println("开始随机：" + System.currentTimeMillis() + "   " + LocalDateTime.now());
        for (int i = 0; i < m; i++) {
            RandomCircles randomCircles = new RandomCircles();
            List<Circle> list = randomCircles.randomCrs(5, 100);
            countList.add(list.size());
        }
        long t2 = System.currentTimeMillis();
        System.out.println("一亿次 随机坐标 time: " + (t2 - t1));
    
        long c = countList.stream().filter(x -> x != 5)
                           .peek(x -> System.out.println(x))
                          .count();
        System.out.println(c);
    }
    
    
    
    @Test
    public void test() {
        try {
            RandomCircles randomCircles = new RandomCircles();
            List<Circle> list = randomCircles.randomCrs(100);
            //任意一个400*200的图片
            String sourcePath = "/Users/wyd/Pictures/bg.png";
            String fontPath = "/Users/wyd/Pictures/HYKaiTiJ.ttf";
            String output = "/Users/wyd/Pictures/1-test.png";
            if (FontUtilities.isWindows) {
                sourcePath = "d:/bg.png";
                fontPath = "d:/data/HYKaiTiJ.ttf";
                output = "d:/2-test.png";
            }
            System.out.println(list);
            System.out.println(list.size());
            
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.BOLD, 25);
    
            //读取原图片信息-
            File file = new File(sourcePath);
            //获取源图像的宽度、高度
            BufferedImage bufferedImage = ImageIO.read(file);
            for (int i = 0; i < list.size(); i++) {
                Circle circle = list.get(i);
                drawCircle(bufferedImage, font, circle, i);
            }
    
            //输出图片
            File sf = new File(output);
            // 保存图片
            ImageIO.write(bufferedImage, "png", sf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    
    
    public void drawCircle(BufferedImage bufferedImage, Font font, Circle circle, int listIndex) {
        
        //创建绘图工具对象
        Graphics2D g2d = bufferedImage.createGraphics();
        //消除文字锯齿
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        //消除画图锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setFont(font);
        g2d.setColor(Color.RED);
        //圆形
        float topLeftX = circle.getX() - circle.getR();
        float topLeftY = circle.getY() - circle.getR();
        float diameter = 2.0f * circle.getR();
        g2d.fill(new Arc2D.Float(topLeftX, topLeftY, diameter, diameter, 0, 360, Arc2D.PIE));
        
        drawGlyphVector(String.valueOf(listIndex), bufferedImage, font, circle);
        
        g2d.dispose();
    }
    
    public void drawGlyphVector(String c, BufferedImage bufferedImage, Font font, Circle circle) {
        Graphics2D g2d = bufferedImage.createGraphics();
        //消除文字锯齿
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        //消除画图锯齿
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);
        
        //设置画笔
        //设置渐变
        //GradientPaint paint = new GradientPaint(20, 20, Color.BLUE, 50, 50, Color.RED, true);
        //g2d.setPaint(paint);
        
        GlyphVector glyphVector = font.createGlyphVector(g2d.getFontRenderContext(), c.toCharArray());
        g2d.drawGlyphVector(glyphVector, circle.getX(), circle.getY());
        
        g2d.dispose();
    }
}
