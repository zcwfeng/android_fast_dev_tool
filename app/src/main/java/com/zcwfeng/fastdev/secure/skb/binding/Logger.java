/*****************************************************************
|
|   Cryptanium Secure Key Box
|
|   $Id: Logger.java 7790 2016-01-05 09:52:35Z kstraupe $
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

import com.cryptanium.skb.SecureData;

public interface Logger
{
    public void logError(String message);

    public void logDebug(String message);

    public void logInfo(String message);

    public void dumpSecureDataInfo(SecureData data);
}
