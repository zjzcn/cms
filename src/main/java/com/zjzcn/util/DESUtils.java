package com.zjzcn.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DESUtils
{
	private static Logger logger = LoggerFactory.getLogger(DESUtils.class);
    /**
     * 3DES加密方式
     */
    public static final String ENCRYPT_3DES = "DESede";
    
    /**
     * DES加密方式
     */
    public static final String ENCRYPT_DES = "DES";
    
    /**
     * AES加密方式
     */
    public static final String ENCRYPT_AES = "AES";
    
    
    private static Charset charset=Charset.forName("UTF-8");

    /**
     * 生成随机密钥，以SecretKey实例返回
     * @param algorithm 加密方式
     * @return SecretKey
     * @throws UtilException UtilException
     * @see [类、类#方法、类#成员]
     */
    public static SecretKey getRandomKey(String algorithm)
    {
        try
        {
            //对称密钥生成器
            KeyGenerator generator = KeyGenerator.getInstance(algorithm);
            
            //初始化此密钥生成器，使其具有确定的密钥长度
            generator.init(new SecureRandom());
            
            //生成密钥
            return generator.generateKey();
        }
        catch (Exception e)
        {
        	logger.error("对称密钥生成异常", e);
        }
        return null;
    }
    
    public static byte[] getKeyBytes(String algorithm)
    {
        return getRandomKey(algorithm).getEncoded();
        
    }
    
    public static byte[] getDESKey()
    {
        return getRandomKey(ENCRYPT_DES).getEncoded();
        
    }
    
    public static byte[] getAESKey()
    {
        return getRandomKey(ENCRYPT_AES).getEncoded();
        
    }
    
    public static byte[] get3DESKey()
    {
        return getRandomKey(ENCRYPT_3DES).getEncoded();
        
    }
    
    /**
     * 根据密钥字符串生成密钥
     * @param keyStr 密钥字符串
     * @param algorithm 编码方式
     * @return 密钥
     * @throws UnsupportedEncodingException 
     * @see [类、类#方法、类#成员]
     */
    public static SecretKey getKey(String keyStr, String algorithm)
    {
        try
        {
            byte[]  keyBytes = keyStr.getBytes(charset);
            return new SecretKeySpec(keyBytes, algorithm);
        }
        catch (Exception e)
        {
        	logger.error("获取密钥异常", e);
        }
        
        return null;
    }
    
    /**
     * AES 加密
     * @param data
     * @param key
     * @return
     */
    public static byte[] encryptAES(byte[] data, byte[] key)
    {
        SecretKey secretKey = new SecretKeySpec(key, ENCRYPT_AES);
        byte[] b = encrypt( data, secretKey, secretKey.getAlgorithm());
        return b;
    }
    
    /**
     * DES 加密
     * @param data
     * @param key
     * @return
     */
    public static byte[] encryptDES(byte[] data, byte[] key)
    {
        SecretKey secretKey = new SecretKeySpec(key, ENCRYPT_DES);
        byte[] b = decrypt( data, secretKey, secretKey.getAlgorithm());
        return b;
    }
    
    /**
     * 3DES 加密
     * @param data
     * @param key
     * @return
     */
    public static byte[] encrypt3DES(byte[] data, byte[] key)
    {
        byte[] bKey = new byte[24];
        
        if (key.length == 16)//解决C# 16byte key的问题
        {
            System.arraycopy(key, 0, bKey, 0, key.length);
            System.arraycopy(key, 0, bKey, 16, 8);
        }
        else if (key.length == 24)
        {
            bKey = key;
        }
        else
        {
        	logger.error("key must be 16-bytes or 24-bytes.");
        }
        
        SecretKey secretKey = new SecretKeySpec(bKey, ENCRYPT_3DES);
        
        byte[] b = encrypt(data, secretKey, secretKey.getAlgorithm());
        return b;
    }
    
    /**
     * AES 解密
     * @param data
     * @param key
     * @return
     */
    public static byte[] decryptAES(byte[] data, byte[] key)
    {
        SecretKey secretKey = new SecretKeySpec(key, ENCRYPT_AES);
        byte[] b = decrypt( data, secretKey, secretKey.getAlgorithm());
        return b;
    }
    
    /**
     * DES 解密
     * @param data
     * @param key
     * @return
     */
    public static byte[] decryptDES(byte[] data, byte[] key)
    {
        SecretKey secretKey = new SecretKeySpec(key, ENCRYPT_DES);
        byte[] b = decrypt( data, secretKey, secretKey.getAlgorithm());
        return b;
    }
    
    /**
     * 3DES 解密
     * @param data
     * @param key
     * @return
     */
    public static byte[] decrypt3DES(byte[] data, byte[] key)
    {
        byte[] bKey = new byte[24];
        
        if (key.length == 16)//解决C# 16byte key的问题
        {
            System.arraycopy(key, 0, bKey, 0, key.length);
            System.arraycopy(key, 0, bKey, 16, 8);
        }
        else if (key.length == 24)
        {
            bKey = key;
        }
        else
        {
        	logger.error("key must be 16-bytes or 24-bytes.");
        }
        
        SecretKey secretKey = new SecretKeySpec(bKey, ENCRYPT_3DES);
        
        byte[] b = decrypt(data, secretKey, secretKey.getAlgorithm());
        return b;
    }
    
    /**
     * 加密
     * @param secretKey 密钥
     * @param data 明文
     * @param algorithm 加密方式
     * @return 密文
     * @throws UtilException
     */
    public static byte[] encrypt(byte[] data, SecretKey secretKey, String algorithm)
    {
        try
        {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        }
        catch (Exception e)
        {
        	logger.error("对称密钥-加密错误:"+algorithm, e);
        }
        
        return null;
    }
    
    /**
     * 解码
     * @param secretKey 密钥
     * @param msg 密文
     * @param algorithm 加密方式
     * @return 明文
     * @throws UtilException UtilException
     */
    public static byte[] decrypt(byte[] data, SecretKey secretKey, String algorithm)
    {
        try
        {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        }
        catch (Exception e)
        {
        	logger.error("对称密钥-解密异常:"+algorithm, e);
        }
        
        return null;
    }
    

    public static void main(String[] args)
    {
        System.out.println(HexUtils.byte2hex(getKeyBytes(ENCRYPT_DES)));
        System.out.println(HexUtils.byte2hex(getKeyBytes(ENCRYPT_3DES)));
        String msg =
            "BpfEymoVsg31QxtqWg5QGws5u/FwWnTRbJAZzBcc6zoY8vyR"
                + "Kx6/XI0Ckb8T0AGc7jyRIUqftHbK87Q5ztTvor9THiGtvcIt9XR4QapxK51QU/ACTtFymVZc9UhiCRUV"
                + "qbDxZ1eEKghach9oNxya/7iooFt6G14gMh0PeYIM8FoUHWWLq+kGn40k15i3SzX/VPLqcJ+FyRPD4/XS"
                + "XovSUmXO1A7Aax98zcnx+gVxM1L10NUsn78e8C2RTmfXoGyppMQmCJned5ZyP2sUlj25iPan05QTAF4S"
                + "PnLLuTI7JVimkZtmdGcv4glz1jpyUdutjwc+SehqaWttoOGEl0F13NaCasvlDVCkk528p8ogSHgVh0D/"
                + "ESy/a1O1mMPUEREPz0N5XC2d8+DhLKwfJ3SpM64laGEPKXF0u1P9hAsHQVjIdWuPpLRqEJ4k486eefhu"
                + "sERDQGTgSueicWLioTOgysDnP0LkejJNnL76nB2VuqpC6rdhQG8MsXAdOkCk9ykOjSTXmLdLNf9U8upw"
                + "n4XJE8Pj9dJei9JSZc7UDsBrH3zNyfH6BXEzUvXQ1Syfvx7wLZFOZ9egbKmkxCYImd53lpX63H+o8nWQ"
                + "anjXEMt7en0hyaZtwYUZ7I0Ckb8T0AGc7jyRIUqftHbK87Q5ztTvot4T7xmYmnlWbm+GTAIqEhr0+ZGS"
                + "cYDOX5dwRjzcInF9iHEBseQzmWwrHACKrCNDuy9kvFdNPIYtph0lRgUBtFxboq5Md9FoPwN2Z7H/kG0C"
                + "A0v3ZVfzMWY3YwXXvCtowVvcz8D7qDpG3NVtg4ZH+uDD4/XSXovSUnwayhdHyXlPOmH3XuaXFv2DXRB4" + "SOeuQA==";
        byte[] data = Base64.decodeBase64(msg.getBytes());
        byte[] key = DigestUtils.md5("a1+b2=c3".getBytes());
        System.out.println(HexUtils.byte2hex(key));
        key[0]=(byte)(key[0]+1);
        System.out.println(HexUtils.byte2hex(key));
        byte[] b = decrypt3DES(data, key);
        System.out.println(new String(b));
        System.out.println(data);
        
        String a = "12345611";
        byte[] k=getKeyBytes(ENCRYPT_3DES);
        byte[] c = DESUtils.encrypt3DES(a.getBytes(), k);
        System.out.println(HexUtils.byte2hex(c));
        k[0]=(byte)(k[0]-1);
        System.out.println(new String(decrypt3DES(c, k)));
    }
}
