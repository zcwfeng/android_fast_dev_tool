/*****************************************************************
|
|   Cryptanium Secure Key Box
|
|   $Id: AESExample.java 7790 2016-01-05 09:52:35Z kstraupe $
|
|   This software is provided to you pursuant to your Software
|   license agreement (SLA) with whiteCryption Corporation
|   ("whiteCryption") and Intertrust Technologies Corporation
|   ("Intertrust"). This software may be used only in accordance
|   with the terms of this agreement.
|
|   Copyright (c) 2000-2016, whiteCryption Corporation. All rights reserved.
|   Copyright (c) 2004-2016, Intertrust Technologies Corporation. All rights reserved.
|
****************************************************************/

package com.zcwfeng.fastdev.secure.skb;

import com.cryptanium.skb.provider.SkbExportedKeySpec;
import com.cryptanium.skb.provider.SkbHighSpeedAesProvider;
import com.cryptanium.skb.provider.SkbProvider;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;

/**
 * This example demonstrates the use of AES algorithm for
 * <ul>
 *  <li>Import/export</li>
 *  <li>Encryption/decryption</li>
 *  <li>Wrap/Unwrap</li>
 * </ul> 
 */
public class AESExample
{
    public static void importExport(Logger logger, String AesCipherProviderName) throws NoSuchProviderException
    {
        try
        {
            String cipherAlgorithm = "AES/CBC/NoPadding";

            // Request key generator and generate AES-128bit key
            KeyGenerator keyGen = KeyGenerator.getInstance("AES", SkbProvider.PROVIDER_NAME);
            SecretKey originalKey = keyGen.generateKey();

            // Export the key
            SecretKeyFactory skbKeyFactory = SecretKeyFactory.getInstance("AES", SkbProvider.PROVIDER_NAME);
            SkbExportedKeySpec exportedKeySpec = (SkbExportedKeySpec)skbKeyFactory.getKeySpec(originalKey,
                                                                                              SkbExportedKeySpec.class);

            // In case we want to save the key in plain, here's how we get the byte sequence:
            byte[] plainEncoded = exportedKeySpec.getEncoded();

            // Then we can import it back:
            // also, note that it is possible to import the key bytes generated with KeyExportTool
            SkbExportedKeySpec newKeySpec = new SkbExportedKeySpec(exportedKeySpec.getAlgorithm(), plainEncoded);
            SecretKey importedKey = skbKeyFactory.generateSecret(newKeySpec);

            // Try to encrypt with originalKey and decrypt with importedKey (they should match):
            Cipher originalKeyEncryptCipher = Cipher.getInstance(cipherAlgorithm, AesCipherProviderName);
            originalKeyEncryptCipher.init(Cipher.ENCRYPT_MODE, originalKey);

            byte data[] = "testtesttesttesttesttesttesttest".getBytes();
            byte[] encrypted = originalKeyEncryptCipher.doFinal(data);

            byte[] iv = originalKeyEncryptCipher.getIV();
            Cipher importedKeyDecryptCipher = Cipher.getInstance(cipherAlgorithm, AesCipherProviderName);

            if (iv == null)
            {
                importedKeyDecryptCipher.init(Cipher.DECRYPT_MODE, importedKey);
            }
            else
            {
                importedKeyDecryptCipher.init(Cipher.DECRYPT_MODE, importedKey, new IvParameterSpec(iv));
            }

            byte[] decrypted = importedKeyDecryptCipher.doFinal(encrypted);

            if (Arrays.equals(data, decrypted))
            {
                logger.logInfo("Encryption/Decryption with Generate/Export/Import successful, AES cipher provider used: " + AesCipherProviderName);
            }
            else
            {
                logger.logError("There have been an error during encryption/decryption, AES cipher provider used: " + AesCipherProviderName);
            }
        }
        catch (NoSuchAlgorithmException e)
        {
            logger.logError("The given algorithm is unsupported");
            return;
        }
        catch (IllegalBlockSizeException e)
        {
            logger.logError("Wrong data length");
            return;
        }
        catch (BadPaddingException e)
        {
            logger.logError("Input data does not have proper padding bytes");
            return;
        }
        catch (InvalidAlgorithmParameterException e)
        {
            logger.logError("Invalid Algorithm Parameters given");
            return;
        }
        catch (InvalidKeyException e)
        {
            logger.logError("Invalid key given");
            return;
        }
        catch (NoSuchPaddingException e)
        {
            logger.logError("Wrong padding given");
            return;
        }
        catch (InvalidKeySpecException e)
        {
            logger.logError("Wrong Key Spec requested");
            return;
        }
    }

    public static void wrapUnwrap(Logger logger, String AesCipherProviderName) throws NoSuchProviderException
    {
        try
        {
            String wrapAlgorithm = "AES/CBC/ISO10126Padding";
            String cipherAlgorithm = "AES/ECB/NoPadding";

            SecretKey generatedKey = null;
            SecretKey wrapKey = null;

            // Generate an AES test key
            KeyGenerator keyGen = KeyGenerator.getInstance("AES", SkbProvider.PROVIDER_NAME);
            generatedKey = keyGen.generateKey();

            // Generate a key to wrap the AES test key
            wrapKey = keyGen.generateKey();
            String generatedKeyAlgorithm = generatedKey.getAlgorithm();

            // Now wrap the generated test key using the generated wrap key
            Cipher skbWrapCipher = Cipher.getInstance(wrapAlgorithm, SkbProvider.PROVIDER_NAME);
            skbWrapCipher.init(Cipher.WRAP_MODE, wrapKey);
            byte[] wrappedKey = skbWrapCipher.wrap(generatedKey);

            // wrapped[] is the safely encrypted key. We can pass it over or save it
            // without being afraid of showing it in plain bytes

            // Unwrap the previously wrapped key
            Cipher skbUnwrapCipher = Cipher.getInstance(wrapAlgorithm, SkbProvider.PROVIDER_NAME);
            skbUnwrapCipher.init(Cipher.UNWRAP_MODE, wrapKey);
            SecretKey unwrappedKey = (SecretKey)skbUnwrapCipher.unwrap(wrappedKey, generatedKeyAlgorithm, Cipher.SECRET_KEY);

            // Encode the message with our generated key
            Cipher encryptCipher = Cipher.getInstance(cipherAlgorithm, AesCipherProviderName);
            encryptCipher.init(Cipher.ENCRYPT_MODE, generatedKey);

            byte data[] = "testtesttesttesttesttesttesttest".getBytes();
            byte[] encrypted = encryptCipher.doFinal(data);

            // Decode the message with unwrapped key (which should match with the initial key)
            Cipher decryptCipher = Cipher.getInstance(cipherAlgorithm, AesCipherProviderName);
            decryptCipher.init(Cipher.DECRYPT_MODE, unwrappedKey);
            byte[] decrypted = decryptCipher.doFinal(encrypted);

            if (Arrays.equals(data, decrypted))
            {
                logger.logInfo("Encryption/Decryption after Wrap/Unwrap successful, AES cipher provider used: " + AesCipherProviderName);
            }
            else
            {
                logger.logError("There have been an error during encryption/decryption with unwrapped key, AES cipher provider used: " + AesCipherProviderName);
            }
        }
        catch (NoSuchAlgorithmException e)
        {
            logger.logError("The given algorithm is unsupported");
            return;
        }
        catch (IllegalBlockSizeException e)
        {
            logger.logError("Wrong data length");
            return;
        }
        catch (BadPaddingException e)
        {
            logger.logError("Input data does not have proper padding bytes");
            return;
        }
        catch (InvalidKeyException e)
        {
            logger.logError("Invalid key given");
            return;
        }
        catch (NoSuchPaddingException e)
        {
            logger.logError("Wrong padding given");
            return;
        }
    }

    public static void run(Logger logger)
    {
        try
        {
            // using default provider for AES cipher
            importExport(logger, SkbProvider.PROVIDER_NAME);
            wrapUnwrap(logger, SkbProvider.PROVIDER_NAME);
            // using high speed provider for AES cipher
            importExport(logger, SkbHighSpeedAesProvider.PROVIDER_NAME);
            wrapUnwrap(logger, SkbHighSpeedAesProvider.PROVIDER_NAME);
        }
        catch (NoSuchProviderException e)
        {
            logger.logError("Failed to find SKB provider");
        }
    }
}
