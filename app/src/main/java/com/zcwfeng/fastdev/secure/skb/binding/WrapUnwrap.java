/*****************************************************************
|
|   Cryptanium Secure Key Box
|
|   $Id: WrapUnwrap.java 7790 2016-01-05 09:52:35Z kstraupe $
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

//The engine class which instantiates the objects:
import com.cryptanium.skb.Engine;
//The SecureData class, used for key storage:
import com.cryptanium.skb.SecureData;
// Parameters used for raw bytes SecureData generation:
import com.cryptanium.skb.parameters.RawBytesParameters;
// Parameters used for passing the IV vector to wrap method:
import com.cryptanium.skb.parameters.AesWrapParameters;
// The Cipher class for wrapping/unwrapping parameters:
import com.cryptanium.skb.Cipher;

import com.cryptanium.skb.SkbException;

/**
 * This example demonstrates the use of AES algorithm for
 * <ul>
 *  <li>Import/export</li>
 *  <li>Encryption/decryption</li>
 *  <li>Wrap/Unwrap</li>
 * </ul> 
 */
public class WrapUnwrap
{
    public static void run(Logger log)
    {
        try
        {
            log.logInfo("Wrap/Unwrap data with AES");

            // Generate 256bit AES key 
            SecureData secureKey = Engine.generateSecureData(SecureData.DataType.SKB_DATA_TYPE_BYTES, new RawBytesParameters(32));

            // Generate 128bit AES key for data wrapping
            SecureData wrappingKey = Engine.generateSecureData(SecureData.DataType.SKB_DATA_TYPE_BYTES,
                                                               new RawBytesParameters(16));

            log.logDebug("SecureData before wrapping");
            log.dumpSecureDataInfo(secureKey);

            // Set the iv vector (leave it as null for a random vector initialization):
            byte[] iv = {
                0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x77, 0x66, 0x55, 0x44, 0x33, 0x22, 0x11, 0x00
            };

            byte[] wrapped = secureKey.wrap(Cipher.CipherAlgorithm.SKB_CIPHER_ALGORITHM_AES_128_CBC,
                                            new AesWrapParameters(iv),
                                            wrappingKey);

            // Unwrap the data:
            SecureData unwrappedKey = Engine.createDataFromWrapped(wrapped,
                                                                   SecureData.DataType.SKB_DATA_TYPE_BYTES,
                                                                   SecureData.DataFormat.SKB_DATA_FORMAT_RAW,
                                                                   Cipher.CipherAlgorithm.SKB_CIPHER_ALGORITHM_AES_128_CBC,
                                                                   null,
                                                                   wrappingKey);

            log.dumpSecureDataInfo(unwrappedKey);
            log.logInfo("Wrap/Unwrap data (AES) done");
        }
        catch (SkbException e)
        {
            log.logDebug(e.getMessage());
        }
        catch (Exception e)
        {
            log.logError("Internal error: " + e.getMessage());
        }
    }
}
