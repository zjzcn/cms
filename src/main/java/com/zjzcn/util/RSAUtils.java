package com.zjzcn.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RSAUtils
{   
	private static Logger logger = LoggerFactory.getLogger(RSAUtils.class);
    /**
     * RSA加密方式
     */
    public static final String ALGORITHM_RSA = "RSA";
    
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;
    
    /**
     * 生成密钥对  
     * @param algorithm 加密方式
     * @return SecretKey
     * @throws UtilException UtilException
     * @see [类、类#方法、类#成员]
     */
    public static KeyPair getKeyPair()
    {
        try
        {
            //生成密钥对  
            KeyPairGenerator keyGen =KeyPairGenerator.getInstance(ALGORITHM_RSA);  
            keyGen.initialize(1024);  
            KeyPair keys= keyGen.generateKeyPair();  
            
            return keys;
        }
        catch (Exception e)
        {
        	logger.error("密钥对生成异常", e);
        }
        return null;
    }
    
    /**
     * 生产平台RSA公钥和私钥
     * @return map 包含公钥指数、私钥指数和模数
     * @throws Exception 异常
     */
    public static String[] getRandomKeys()
    {
        try
        {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM_RSA);
            // 密钥位数
            keyPairGen.initialize(1024);
            // 密钥对
            KeyPair keyPair = keyPairGen.generateKeyPair();
            
            // 公钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            
            // 私钥
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            
            String[] keys={ publicKey.getModulus().toString(),  //modulus
                publicKey.getPublicExponent().toString(),   //publicExponent
                privateKey.getPrivateExponent().toString()  //privateExponent
            };
            return keys;
        }
        catch (Exception e)
        {
        	logger.error("RSA密钥对生成异常", e);
        }
        return null;
    }
    
    /**
     * 根据公钥指数和模数获取公钥
     * @param modulus 模数 
     * @param publicExponent 指数 
     * @return 公钥
     */
    public static PublicKey getPublicKey(String modulus, String publicExponent)
    {
        try
        {
            BigInteger m = new BigInteger(modulus);
            
            BigInteger e = new BigInteger(publicExponent);
            
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
            
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            
            return publicKey;
        }
        catch (Exception e)
        {
        	logger.error("RSA公钥生成异常", e);
        }
        return null;
    }
    
    /**
     * 根据模数和私钥指数获取私钥
     * @param modulus 模数
     * @param privateExponent 私钥指数
     * @return 私钥
     */
    public static PrivateKey getPrivateKey(String modulus, String privateExponent)
    {
        try
        {
            BigInteger m = new BigInteger(modulus);
            
            BigInteger e = new BigInteger(privateExponent);
            
            RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(m, e);
            
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            
            return privateKey;
        }
        catch (Exception e)
        {
        	logger.error("RSA私钥生成异常", e);
        }
        return null;
    }
    
    /**
     * 公钥加密
     * @param data
     * @param modulus
     * @param publicExponent
     * @return
     */
    public static byte[] encryptByPublicKey(byte[] data, String modulus, String publicExponent)
    {
        PublicKey publicKey=getPublicKey(modulus, publicExponent);
        return encrypt(data, publicKey);
    }
    
    /**
     * 私钥签名
     * @param data
     * @param modulus
     * @param publicExponent
     * @return
     */
    public static byte[] encryptByPrivateKey(byte[] data, String modulus, String publicExponent)
    {
        PrivateKey privateKey=getPrivateKey(modulus, publicExponent);
        return encrypt(data, privateKey);
    }
    
    /**
     * 公钥验收
     * @param data
     * @param modulus
     * @param publicExponent
     * @return
     */
    public static byte[] decryptByPublicKey(byte[] data, String modulus, String publicExponent)
    {
        PublicKey publicKey=getPublicKey(modulus, publicExponent);
        return decrypt(data, publicKey);
    }
    
    /**
     * 私钥解密
     * @param data
     * @param modulus
     * @param publicExponent
     * @return
     */
    public static byte[] decryptByPrivateKey(byte[] data, String modulus, String publicExponent)
    {
        PrivateKey privateKey=getPrivateKey(modulus, publicExponent);
        return decrypt(data, privateKey);
    }
    
    /**
     * 加密
     * @param data
     * @param key
     * @return
     */
    public static byte[] encrypt(byte[] data, Key key)
    {
        try
        {
            Cipher cipher = Cipher.getInstance(key.getAlgorithm());
            // 加密
            cipher.init(Cipher.ENCRYPT_MODE, key);
            
            int dataLen=data.length;
            
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            // 对数据分段加密
            for (int i=0, offset = 0; dataLen - (offset = i*MAX_ENCRYPT_BLOCK) > 0; i++) 
            {
                byte[] cache;
                if (dataLen - offset > MAX_ENCRYPT_BLOCK) 
                {
                    cache = cipher.doFinal(data, offset, MAX_ENCRYPT_BLOCK);
                } else 
                {
                    cache = cipher.doFinal(data, offset, dataLen - offset);
                }
                
                out.write(cache, 0, cache.length);
            }
            
            byte[] encryptedData = out.toByteArray();
            out.close();
            return encryptedData;
        }
        catch (Exception e)
        {
        	logger.error("RSA加密异常", e);
        }
        return null;
    }
    
    /**
     * 解密
     * @param data
     * @param key
     * @return
     */
    public static byte[] decrypt( byte[] data, Key key)
    {
        try
        {
            Cipher cipher = Cipher.getInstance("RSA");
            //解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            
            int dataLen=data.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            // 对数据分段
            for (int i=0, offset = 0; data.length - (offset = i*MAX_DECRYPT_BLOCK) > 0; i++) 
            {
                byte[] cache;
                if (dataLen - offset > MAX_DECRYPT_BLOCK) 
                {
                    cache = cipher.doFinal(data, offset, MAX_DECRYPT_BLOCK);
                } else 
                {
                    cache = cipher.doFinal(data, offset, dataLen - offset);
                }
                
                out.write(cache, 0, cache.length);
            }
            
            byte[] decryptedData = out.toByteArray();
            out.close();
            return decryptedData;
        }
        catch (Exception e)
        {
        	logger.error("RSA解密异常", e);
        }
        return null;
    }
    public static void main(String[] args) throws UnsupportedEncodingException
    {
        String[] keys=getRandomKeys();
        //加解密
        System.out.println(new String(decryptByPrivateKey(encryptByPublicKey("123456".getBytes(), keys[0], keys[1]), keys[0], keys[2])));
        //签名、验收
        System.out.println(new String(decryptByPublicKey(encryptByPrivateKey("123456".getBytes(), keys[0], keys[1]), keys[0], keys[2])));
    }
}
