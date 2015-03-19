package com.zjzcn.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/** 
 * CheckCode.java 
 *  
 * @author King<br/> 
 *          
 * @Description 验证码生成类 
 * @since 1.0.0 
 * @Date 2012-2-29下午1:50:25 
 */
public class Captcha
{
    public static final String CAPTCHA_SESSION = "captchaSession";
    private static final int EACH_WIDTH = 14;
    
    private int count = 4;
    private int height = 24;
    private int width = EACH_WIDTH * count;
    private boolean isTwist;
    
    private Random random = new Random();
    
    private BufferedImage captchaImage;
    
    private String captchaString;
    
    public Captcha()
    {
        init();
    }
    public Captcha(int count, boolean isTwist)
    {
        this.count = count;
        this.width = EACH_WIDTH * count;
        this.isTwist = isTwist;
        init();
    } 
    
    private void init()
    {
        captchaString = createRandomString();
        
        captchaImage = createImage();
        captchaImage = drawString(captchaImage, captchaString);
        captchaImage = drawDisturbLine(captchaImage);
        if(isTwist)
        {
            captchaImage = twistImage(captchaImage);
        }
    }
    /** 
     *  
     * @Description:随机产生的验证码 
     * @since 1.0.0 
     * @Date:2012-3-1 上午10:20:05 
     * @return String 
     */
    private String createRandomString()
    {
        StringBuffer randomCode = new StringBuffer();
        
        char[] codeChars = "1234567890".toCharArray();
        
        for (int i = 0; i < count; i++)
        {
            randomCode.append(codeChars[random.nextInt(codeChars.length - 1)]);
        }
        
        return randomCode.toString();
    }
    
    private BufferedImage createImage()
    {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bi.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        
        return bi;
    }
    
    private BufferedImage drawString(BufferedImage bi, String randomCode)
    {
        Graphics2D graphics = bi.createGraphics();
        
        graphics.setFont(new Font("Fixedsys", Font.BOLD, height - 2));
        for (int i = 0; i < randomCode.length(); i++)
        {
            graphics.setColor(createRandomColor(20, 150));
            graphics.drawString(randomCode.charAt(i) + "", 8 + i * width / (randomCode.length() + 1), height - 4);
        }
        
        return bi;
    }
    
    /** 
     *  
     * @Description:创建颜色 
     * @since 1.0.0 
     * @Date:2012-2-29 下午4:47:14 
     * @return Color 
     */
    private Color createColor()
    {
        Color color[] = new Color[10];
        color[0] = new Color(113, 31, 71);
        color[1] = new Color(37, 0, 37);
        color[2] = new Color(111, 33, 36);
        color[3] = new Color(0, 0, 112);
        color[4] = new Color(14, 51, 16);
        color[5] = new Color(1, 1, 1);
        color[6] = new Color(72, 14, 73);
        color[7] = new Color(65, 67, 29);
        color[8] = new Color(116, 86, 88);
        color[9] = new Color(41, 75, 71);
        
        return color[random.nextInt(10)];
    }
    
    /*
     * 给定范围获得随机颜色
     */
    private Color createRandomColor(int start, int end)
    {
        Random random = new Random();
        if (start > 255)
            start = 255;
        if (end > 255)
            end = 255;
        int r = start + random.nextInt(end - start);
        int g = start + random.nextInt(end - start);
        int b = start + random.nextInt(end - start);
        return new Color(r, g, b);
    }
    
    /** 
     *  
     * @Description:画干扰线使图象中的认证码不易被其它程序探测到 
     * @since 1.0.0 
     * @Date:2012-2-29 下午4:28:23 
     * @param graphics 
     *            void 
     */
    private BufferedImage drawDisturbLine(BufferedImage bi)
    {
        Graphics2D graphics = bi.createGraphics();
        int x = 0;
        int y = 0;
        int xl = 0;
        int yl = 0;
        for (int i = 0; i < 15; i++)
        {
            graphics.setColor(createColor());
            x = random.nextInt(width);
            y = random.nextInt(height);
            xl = random.nextInt(20);
            yl = random.nextInt(10);
            graphics.drawLine(x, y, x + xl, y + yl);
        }
        
        return bi;
    }
    
    /** 
     *  
     * @Description:正弦曲线Wave扭曲图片 
     * @since 1.0.0 
     * @Date:2012-3-1 下午12:49:47 
     * @return BufferedImage 
     */
    private BufferedImage twistImage(BufferedImage bi)
    {
        double dMultValue = random.nextInt(7) + 3;// 波形的幅度倍数，越大扭曲的程序越高，一般为3  
        double dPhase = random.nextInt(6);// 波形的起始相位，取值区间（0-2＊PI）  
        
        BufferedImage twistBuffImg = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = twistBuffImg.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, bi.getWidth(), bi.getHeight());
        
        for (int i = 0; i < twistBuffImg.getWidth(); i++)
        {
            for (int j = 0; j < twistBuffImg.getHeight(); j++)
            {
                int nOldX = getXPosition4Twist(dPhase, dMultValue, twistBuffImg.getHeight(), i, j);
                int nOldY = j;
                if (nOldX >= 0 && nOldX < twistBuffImg.getWidth() && nOldY >= 0 && nOldY < twistBuffImg.getHeight())
                {
                    twistBuffImg.setRGB(nOldX, nOldY, bi.getRGB(i, j));
                }
            }
        }
        
        return twistBuffImg;
    }
    
    /** 
     *  
     * @Description:获取扭曲后的x轴位置 
     * @since 1.0.0 
     * @Date:2012-3-1 下午3:17:53 
     * @param dPhase 
     * @param dMultValue 
     * @param height 
     * @param xPosition 
     * @param yPosition 
     * @return int 
     */
    private int getXPosition4Twist(double dPhase, double dMultValue, int height, int xPosition, int yPosition)
    {
        double PI = 3.1415926535897932384626433832799; // 此值越大，扭曲程度越大  
        double dx = (double)(PI * yPosition) / height + dPhase;
        double dy = Math.sin(dx);
        return xPosition + (int)(dy * dMultValue);
    }
    
    /** 
     *  
     * @Description:将图像进行输出到流 
     * @since 1.0.0 
     * @Date:2012-3-1 上午11:56:19 
     * @param pathName 
     * @return String 
     */
    public void outputCaptcha(OutputStream outputStream)
    {
        try
        {
            ImageIO.write(getCaptchaImage(),"JPEG", outputStream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public BufferedImage getCaptchaImage()
    {
        return captchaImage;
    }
    
    public String getCaptchaString()
    {
        return captchaString;
    }
    

    
    public static void main(String[] args) throws FileNotFoundException
    {
        long startTime=System.currentTimeMillis();
        for (int i = 0; i < 1; i++)
        {
            Captcha captcha = new Captcha(8, false);
            System.out.println(captcha.getCaptchaString());
            
            String filePathName = "C:\\" + i+ ".jpg";
            captcha.outputCaptcha(new FileOutputStream(filePathName));
        }
        
        long endTime=System.currentTimeMillis();
        
        System.out.println("time:"+(endTime-startTime));
    }
    
}