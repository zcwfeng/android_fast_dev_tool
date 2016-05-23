/*****************************************************************
|
|   Cryptanium Secure Key Box
|
|   $Id: ImportExport.java 7790 2016-01-05 09:52:35Z kstraupe $
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
//Parameters, used for raw bytes SecureData generation:
import com.cryptanium.skb.parameters.RawBytesParameters;

import com.cryptanium.skb.SkbException;

/**
 * This example demonstrates the use of AES algorithm for
 * <ul>
 *  <li>Import/export</li>
 *  <li>Encryption/decryption</li>
 *  <li>Wrap/Unwrap</li>
 * </ul> 
 */
public class ImportExport
{

    public static void run(Logger log)
    {
        try
        {
            log.logInfo("Key import/export");
            /* Exporting secure data: */

            // Generate 128bit AES key
            SecureData generated = Engine.generateSecureData(SecureData.DataType.SKB_DATA_TYPE_BYTES, new RawBytesParameters(16));

            log.logDebug("SecureData info before export:");
            log.dumpSecureDataInfo(generated);

            // Export secure data. Persistent is used in order to save / load data later. 
            // As of now, the second parameter should always be null.
            byte[] exportedBytes = generated.export(SecureData.ExportTarget.SKB_EXPORT_TARGET_PERSISTENT, null);

            // Now, you can save those bytes for later use.

            /* Importing secure data: */

            SecureData imported = Engine.createDataFromExported(exportedBytes);

            log.logDebug("SecureData info after import:");
            log.dumpSecureDataInfo(imported);

            log.logDebug("Key import/export done");
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
