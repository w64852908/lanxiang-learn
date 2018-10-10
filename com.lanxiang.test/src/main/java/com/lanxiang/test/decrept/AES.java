package com.meituan.service.movie.venice.core.common.utils.aes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class AES {
    private static final Logger LOGGER = LoggerFactory.getLogger(AES.class);

    private static final String KEY_GENERATION_ALG = "PBKDF2WithHmacSHA1";
    private static final int HASH_ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private static byte[] SALT = { 1, 3, 9, 6, 9, 4, 4, 4, 0, 2, 0xA, 0xB, 0xC, 0xD, 0xE, 0xF };
    private static final String CIPHER_MODE_PADDING = "AES/CBC/PKCS5Padding";
    private static SecretKeyFactory KEY_FACTORY = null;
    private static IvParameterSpec IV_SPEC = new IvParameterSpec(new byte[] { 0xA, 1, 0xB, 5, 4, 0xF, 7, 9, 0x17, 3, 1, 6, 8, 0xC, 0xD, 91 });

    static {
        try {
            KEY_FACTORY = SecretKeyFactory.getInstance(KEY_GENERATION_ALG);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private static SecretKeySpec convertAESKey(String key) {
        try {
            KeySpec keySpec = new PBEKeySpec(key.toCharArray(), SALT, HASH_ITERATIONS, KEY_LENGTH);
            SecretKey secretKey = KEY_FACTORY.generateSecret(keySpec);
            byte[] encodedSecretKeyByteArray = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(encodedSecretKeyByteArray, "AES");
            return secretKeySpec;
        } catch (InvalidKeySpecException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public static String encrypt(String plainText, String key) {
        SecretKeySpec secretKeySpec = convertAESKey(key);
        byte[] cipheredText = encrypt(CIPHER_MODE_PADDING, secretKeySpec, IV_SPEC, plainText.getBytes());
        String encodedText = Base64Encoder.encode(cipheredText);
        return encodedText;
    }

    public static String decrypt(String encodeText, String key) {
        byte[] s = Base64Decoder.decodeToBytes(encodeText);
        SecretKeySpec skforAES = convertAESKey(key);
        String decrypted = new String(decrypt(CIPHER_MODE_PADDING, skforAES, IV_SPEC, s));
        return decrypted;
    }

    /**
     * 加密
     * 
     * @param cmp
     *            填充方式
     * @param sk
     *            密钥
     * @param IV
     *            向量
     * @param msg
     *            需要加密的内容
     * @return 返回加密结果
     */
    private static byte[] encrypt(String cmp, SecretKey sk, IvParameterSpec IV, byte[] msg) {
        try {
            Cipher c = Cipher.getInstance(cmp);
            c.init(Cipher.ENCRYPT_MODE, sk, IV);
            return c.doFinal(msg);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 解密
     * 
     * @param cmp
     *            填充函数
     * @param sk
     *            密钥
     * @param IV
     *            向量
     * @param ciphertext
     *            需要解密内容
     * @return 返回解密结果
     */
    private static byte[] decrypt(String cmp, SecretKey sk, IvParameterSpec IV, byte[] ciphertext) {
        try {
            Cipher c = Cipher.getInstance(cmp);
            c.init(Cipher.DECRYPT_MODE, sk, IV);
            return c.doFinal(ciphertext);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public static void main(String[] args) {

        String key = "123456";
        String mobile = "13370170697";
        String mobileSec = AES.encrypt(mobile, key);
        System.out.println(mobileSec);

        String decodeMobile = AES.decrypt(mobileSec, key);
        System.out.println(decodeMobile);

//        String printNo = "wLVz3DQyavjkqPr8Ttijwv8LVsDdHID7jaaf+O7Z79A=";
//        String verifyCode = "cotiafKlZBKo3AcujnfYTTL5rA0aww8G1Z0ouE0LwlE=";
//
//        String test = AES.encrypt(printNo, key);
//        System.out.println(AES.decrypt(test, key));
//
//        System.out.println(AES.decrypt(printNo, key));
//        System.out.println(AES.decrypt(verifyCode, key));
    }
}
