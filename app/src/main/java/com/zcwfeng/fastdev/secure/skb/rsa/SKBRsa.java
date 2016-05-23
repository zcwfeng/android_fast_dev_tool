package com.zcwfeng.fastdev.secure.skb.rsa;

import android.util.Log;

import com.cryptanium.skb.provider.SkbExportedKeySpec;
import com.cryptanium.skb.provider.SkbHighSpeedAesProvider;
import com.cryptanium.skb.provider.SkbProvider;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
/**
 * Created by David.zhang on 16/5/13.
 * Description：
 */
public class SKBRsa {
    static byte[] EncryptedBuf;
    static byte[] ExportedKey;

    public  static void descSKB() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String HighSpeedProvider = SkbHighSpeedAesProvider.PROVIDER_NAME;

        String Provider = SkbProvider.PROVIDER_NAME;
        //            mjk add begin
        String cipherAlgorithm = "RSA/ECB/PKCS1Padding"; //"RSA/CBC/PKCS1Padding"
        //            // Request key generator and generate AES-128bit key
//            KeyGenerator keyGen = KeyGenerator.getInstance("RSA", Provider);
//            SecretKey originalKey = keyGen.generateKey();
//
//            // Export the key
        SecretKeyFactory skbKeyFactory = SecretKeyFactory.getInstance("RSA", Provider);
//            SkbExportedKeySpec exportedKeySpec = (SkbExportedKeySpec)skbKeyFactory.getKeySpec(originalKey,
//                    SkbExportedKeySpec.class);
//
//            // In case we want to save the key in plain, here's how we get the byte sequence:
//            byte[] plainEncoded = exportedKeySpec.getEncoded();

        // Then we can import it back:
        // also, note that it is possible to import the key bytes generated with KeyExportTool
        SkbExportedKeySpec newKeySpec = new SkbExportedKeySpec(cipherAlgorithm, ExportedKey);
        SecretKey importedKey = skbKeyFactory.generateSecret(newKeySpec);

        // Try to encrypt with originalKey and decrypt with importedKey (they should match):
        Cipher originalKeyEncryptCipher = Cipher.getInstance(cipherAlgorithm, Provider);
        originalKeyEncryptCipher.init(Cipher.DECRYPT_MODE, importedKey);

        //            mjk add end
// TODO: encrypt authData to authString using SKB JCA provider
        byte authString[] = originalKeyEncryptCipher.doFinal(EncryptedBuf);


        Log.e("zcw",authString.toString());
    }









    public static void  init(String encrypt,String key) {
        EncryptedBuf = encrypt.getBytes();
        ExportedKey = key.getBytes();
    }


}
