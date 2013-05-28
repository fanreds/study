package pl.edu.pk.security;

import gnu.crypto.jce.GnuCrypto;

import javax.crypto.*;
import javax.crypto.spec.*;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.io.*;
import java.security.*;

/**
 * Created with IntelliJ IDEA.
 * User: klex
 * Date: 5/23/13
 * Time: 9:35 AM
 */
@Named
@ViewScoped
public class SecurityTester implements Serializable {
    KeyStoreSpi keyStoreSpi;
    KeyStore keyStore;
    PrivateKey privateKey;
    PublicKey publicKey;
    KeyGenerator keyGenerator;
    KeyPairGenerator keyPairGenerator;
    KeyPairGeneratorSpi keyPairGeneratorSpi;
    Signature signature;
    Cipher cipher;
    Key key;
    IvParameterSpec ivParameterSpec;
    String kk;
    //    public String MessageDigestTest(){
//        try {
//            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    SecretKey desKey;
    byte[] encrypted;
    byte[] decrypted;

    private static byte[] encrypt(byte[] inpBytes, PublicKey key,
                                  String xform) throws Exception {
        Cipher cipher = Cipher.getInstance(xform);
        String pro = cipher.getProvider().getName();
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(inpBytes);
    }

    private static byte[] decrypt(byte[] inpBytes, PrivateKey key,
                                  String xform) throws Exception {
        Cipher cipher = Cipher.getInstance(xform);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(inpBytes);
    }

    public String KeyStoreTest() {
        try {
//            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//            keyPairGenerator.initialize(512);
            keyStore = KeyStore.getInstance("JKS");
            InputStream inputStream = getClass().getResourceAsStream("/test.jks");
            keyStore.load(inputStream, "test12".toCharArray());
            privateKey = (PrivateKey) keyStore.getKey("test", "test12".toCharArray());
//            KeyPair keyPair = keyPairGenerator.genKeyPair();
//            privateKey = keyPair.getPrivate();

            FileOutputStream sFile = new FileOutputStream("signature");
            ObjectOutputStream out = new ObjectOutputStream(sFile);
            FileInputStream msgFile = (FileInputStream) getClass().getResourceAsStream("/test.txt");

            /**
             * signature algorithm must correspond to the key obtained
             * from the keystore
             */
            Signature s = Signature.getInstance("SHA1withRSA");
            s.initSign(privateKey);
            byte[] buffer = new byte[1024];
            int result;
            while ((result = msgFile.read(buffer)) < -1) {
                s.update(buffer, 0, result);
            }
            out.writeObject(s.sign());

            PublicKey publicKey = (PublicKey) keyStore.getKey("test", "test12".toCharArray());
//            publicKey = keyPair.getPublic();
            //**********************************************************************************************
            /**
             * signature algorithm must correspond to the key obtained
             * from the keystore
             */
            Signature s1 = Signature.getInstance("SHA1withRSA");
            s1.initVerify(publicKey);
            FileInputStream sFile1 = new FileInputStream("signature");
            ObjectInputStream input = new ObjectInputStream(sFile1);
            byte[] signature = (byte[]) input.readObject();
            FileInputStream msgFile1 = (FileInputStream) getClass().getResourceAsStream("/test.txt");
            byte[] buffer1 = new byte[1024];
            int result1;
            while ((result1 = msgFile1.read(buffer1)) > -1)
                s1.update(buffer1, 0, result1);
            if (s1.verify(signature)) {
                return "Message has not been modified";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String CipherTest() {
        //Szyfrowanie z poziomu interfejsu programistycznego (API)
        try {
            keyGenerator = KeyGenerator.getInstance("DES");
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            key = keyGenerator.generateKey();

            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] input = "Losowa wiadomosc".getBytes();
            byte[] encrypted = cipher.doFinal(input);
            byte[] iv = cipher.getIV();         //wektor inicjujacy
            key = new SecretKeySpec(key.getEncoded(), 0, iv.length, "DES");
            ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
            byte[] output = cipher.doFinal(encrypted);
            return "CipherTest odkodowana wartosc: " + new String(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void encryptedCipherFileTest() throws IOException {
        FileInputStream original = (FileInputStream) getClass().getResourceAsStream("/test.txt");

        DESKeySpec dks = null;
        try {
            byte[] bytes = new byte[64];
            int len = original.read(bytes);
            String str2 = new String(bytes, "UTF-8");
            dks = new DESKeySpec("password".getBytes());
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            desKey = skf.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE
            cipher.init(Cipher.ENCRYPT_MODE, desKey);
            CipherInputStream cis = new CipherInputStream(original, cipher);
            encrypted = new byte[64];
            cis.read(encrypted);


            String str = new String(encrypted, "UTF-8");
            original.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void decryptedCipherFileTest() throws IOException {
        DESKeySpec dks = null;
        try {
            dks = new DESKeySpec("password".getBytes());
            Cipher cipher = Cipher.getInstance("DES"); // DES/ECB/PKCS5Padding for SunJCE
            cipher.init(Cipher.DECRYPT_MODE, desKey);
//            CipherOutputStream cos = new CipherOutputStream(out, cipher);
            byte[] output = cipher.doFinal(encrypted);
            String str2 = new String(output, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getKk() {
        return kk;
    }

    public String PBETest() {

        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            Key sharedKey = kg.generateKey();
            String password = "password";
            byte[] salt = new byte[8];
            SecureRandom rand = new SecureRandom();
            rand.nextBytes(salt);
            PBEParameterSpec spec = new PBEParameterSpec(salt, 30);
            PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
            SecretKey passKey = f.generateSecret(keySpec);
            Cipher c = Cipher.getInstance("PBEWithMD5AndDES");

            /**
             * wrap (encrypt) key
             */
            c.init(Cipher.WRAP_MODE, passKey, spec);
            byte[] wrappedKey = c.wrap(sharedKey);
            kk = new String(sharedKey.getEncoded());

            /**
             * unwrap (decrypt) key
             */
            c.init(Cipher.UNWRAP_MODE, passKey, spec);
            Key unwrappedKey = c.unwrap(wrappedKey, "AES", Cipher.SECRET_KEY);
            encryptedCipherFileTest();
            decryptedCipherFileTest();
            return "PBETest odkodowana wartosc: " + new String(unwrappedKey.getEncoded());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;


    }

    public String getEncrypted() throws UnsupportedEncodingException {
        return new String(encrypted, "UTF-8");
    }

    public String getDecrypted() throws UnsupportedEncodingException {
        return new String(decrypted, "UTF-8");
    }

    public void AsymmetricTest() {
        try {
            Security.addProvider(new GnuCrypto());
            keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            publicKey = keyPair.getPublic();
            privateKey = keyPair.getPrivate();

            String text = "you'll never walk alone";

//            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding",new GnuCrypto());
            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding", new GnuCrypto());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            encrypted = cipher.doFinal(text.getBytes());

//            cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            decrypted = cipher.doFinal(encrypted);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String test() throws Exception {
//        Security.insertProviderAt(new GnuCrypto(), 1);
//        String xform = "KHAZAD";
//        String xform = "AES/CBC/PKCS5Padding";
        String xform = "RSA/ECB/PKCS1Padding";
        // Generate a key-pair
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(512); // 512 is the keysize.
        KeyPair kp = kpg.generateKeyPair();
        PublicKey pubk = kp.getPublic();
        PrivateKey prvk = kp.getPrivate();
        byte[] dataBytes =
                "J2EE Security for Servlets, EJBs and Web Services".getBytes();
        byte[] encBytes = encrypt(dataBytes, pubk, xform);
        byte[] decBytes = decrypt(encBytes, prvk, xform);

        boolean expected = java.util.Arrays.equals(dataBytes, decBytes);
        return ("Test " + (expected ? "SUCCEEDED!" : "FAILED!") + new String(decBytes));
    }
}
