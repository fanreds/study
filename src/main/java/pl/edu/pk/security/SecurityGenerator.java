package pl.edu.pk.security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 5/27/13
 * Time: 3:59 PM
 */
public class SecurityGenerator {

    String ALGORITHM_RSA = "RSA";
    KeyGenerator keyGenerator;
    KeyPairGenerator keyPairGenerator;
    KeyPair keyPair;
    Cipher cipher;

    public void initRSA() {
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_RSA);
        } catch (Exception e) {
            e.printStackTrace();
        }
        keyPairGenerator.initialize(512);
    }

    public void initCipherRSA() {
        try {
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initCipherDES() {
        try {
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] getEncoded(byte[] text, byte[] key) {
        try {
            EncodedKeySpec spec = new X509EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            PublicKey publicKey = keyFactory.generatePublic(spec);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] getDecoded(byte[] text, byte[] key) {
        try {
            EncodedKeySpec spec = new PKCS8EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            PrivateKey privateKey = keyFactory.generatePrivate(spec);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] getIV() {
        return cipher.getIV();
    }

    public void generateKeyPairDSA() {
        keyPair = keyPairGenerator.generateKeyPair();
    }

    public byte[] getPrivateKey() {
        return keyPair.getPrivate().getEncoded();
    }

    public byte[] getPublicKey() {
        return keyPair.getPublic().getEncoded();
    }

    public void initAES() {
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] getEncodedDES(byte[] input, byte[] key) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, "DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] getDecodedDES(byte[] input, byte[] key, byte[] iv) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, "DES");
            IvParameterSpec spec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);
            return cipher.doFinal(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void initDES() {
        try {
            keyGenerator = KeyGenerator.getInstance("DES");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] generateSecretKey() {
        return keyGenerator.generateKey().getEncoded();
    }

}
