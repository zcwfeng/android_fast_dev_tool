/*****************************************************************
|
|   Cryptanium Secure Key Box
|
|   $Id: AesCipher.java 7790 2016-01-05 09:52:35Z kstraupe $
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

package com.zcwfeng.fastdev.secure.skb.binding;

import com.cryptanium.skb.Cipher;
import com.cryptanium.skb.Engine;
import com.cryptanium.skb.SecureData;
import com.cryptanium.skb.SkbException;
import com.cryptanium.skb.parameters.CipherParameters;
import com.cryptanium.skb.parameters.CtrModeCipherParameters;
import com.cryptanium.skb.parameters.RawBytesParameters;

import java.util.Arrays;

//The engine class which instantiates the objects:
//The SecureData class, used for key storage:
// The Cipher class for data encoding/decoding
// Parameters used for raw bytes SecureData generation:
// Generic cipher parameters
// Parameters used for CTR mode Cipher generation

/**
 * This example demonstrates the use of AES algorithm for
 * <ul>
 *  <li>Import/export</li>
 *  <li>Encryption/decryption</li>
 *  <li>Wrap/Unwrap</li>
 * </ul> 
 */
public class AesCipher
{
    public static final String EXAMPLE_TEXT = "The quick brown fox jumps over the lazy dog and feels"
                                              + " as if he were in the seventh heaven of typography "
                                              + "together with Hermann Zapf, the most famous artist of the...";

    public static void runCipher(Cipher.CipherAlgorithm algorithm, byte[] iv, CipherParameters parameters, Logger log)
        throws Exception
    {
        log.logDebug("Running cipher: " + algorithm.name());

        // Generate 128bit AES key
        SecureData cipherKey = Engine.generateSecureData(SecureData.DataType.SKB_DATA_TYPE_BYTES, new RawBytesParameters(16));

        // Initialize the encrypt cipher object
        Cipher encryptCipher = Engine.createCipher(algorithm,
                                                   Cipher.CipherDirection.SKB_CIPHER_DIRECTION_ENCRYPT,
                                                   0,
                                                   parameters,
                                                   cipherKey);

        // Important: Input data should be the multiple of 16
        byte[] input = (EXAMPLE_TEXT.substring(0, 128)).getBytes();
        log.logDebug("Input data: " + input + "Length: " + input.length);

        // Get the size of the array we'll need (at most) for output
        // note that we pass null in place of output array
        int outputSize = encryptCipher.processBuffer(input, 0, input.length, iv, null, 0);

        byte[] output = new byte[outputSize];

        // In case our encrypted data is smaller than expected
        int actualSize = encryptCipher.processBuffer(input, 0, input.length, iv, output, 0);

        // Initialize the encrypt cipher object
        Cipher decryptCipher = Engine.createCipher(algorithm,
                                                   Cipher.CipherDirection.SKB_CIPHER_DIRECTION_DECRYPT,
                                                   0,
                                                   parameters,
                                                   cipherKey);

        // Try to get maximum possible decrypted data length:
        // Note the use of actualSize variable
        int decryptedSize = decryptCipher.processBuffer(output, 0, actualSize, iv, null, 0);

        byte decrypted[] = new byte[decryptedSize];

        int actualDecryptedSize = decryptCipher.processBuffer(output, 0, actualSize, iv, decrypted, 0);
        if (actualDecryptedSize != decryptedSize)
        {
            log.logError("Decrypted data size differs from the expected data size");
        }

        log.logDebug("Decrypted data: " + decrypted + "Length: " + decrypted.length);

        if (Arrays.equals(input, decrypted))
        {
            log.logDebug("Encrypted data is the same as decrypted data");
        }
        else
        {
            log.logError("Encrypted and decrypted data differ");
        }

        log.logDebug("Encrypted data: " + output);
    }

    public static void run(Logger log)
    {
        try
        {
            log.logInfo("Encrypt/Decrypt data (AES)");

            runCipher(Cipher.CipherAlgorithm.SKB_CIPHER_ALGORITHM_AES_128_ECB, null, null, log);

            //Obtain initialization vector
            byte[] iv = {
                0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x77, 0x66, 0x55, 0x44, 0x33, 0x22, 0x11, 0x00
            };
            runCipher(Cipher.CipherAlgorithm.SKB_CIPHER_ALGORITHM_AES_128_CBC, iv, null, log);
            runCipher(Cipher.CipherAlgorithm.SKB_CIPHER_ALGORITHM_AES_128_CTR, iv, new CtrModeCipherParameters(16), log);

            log.logInfo("Encrypt/Decrypt data (AES) done");
        }
        catch (SkbException e)
        {
            log.logError(e.getMessage());
        }
        catch (Exception e)
        {
            log.logError("Internal error: " + e.getMessage());
        }
    }
}
