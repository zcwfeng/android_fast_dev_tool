package com.zcwfeng.fastdev.secure;


import android.content.Context;

import com.facebook.android.crypto.keychain.SharedPrefsBackedKeyChain;
import com.facebook.crypto.Crypto;
import com.facebook.crypto.Entity;
import com.facebook.crypto.exception.CryptoInitializationException;
import com.facebook.crypto.exception.KeyChainException;
import com.facebook.crypto.keychain.KeyChain;
import com.facebook.crypto.util.SystemNativeCryptoLibrary;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CustomKeyChain implements KeyChain {


    /**
     * encryptingContent
     *
     * @param context
     * @param file
     * @param plainTextBytes
     * @param entity
     */
    public void encryptingContent(Context context, File file, byte[] plainTextBytes, Entity entity) {
        try {
            // Creates a new Crypto object with default implementations of
            // a key chain as well as native library.
            Crypto crypto = new Crypto(
                    new SharedPrefsBackedKeyChain(context),
                    new SystemNativeCryptoLibrary());

            // Check for whether the crypto functionality is available
            // This might fail if android does not load libaries correctly.
            if (!crypto.isAvailable()) {
                return;
            }

            OutputStream fileStream = new BufferedOutputStream(
                    new FileOutputStream(file));

            // Creates an output stream which encrypts the data as
            // it is written to it and writes it out to the file.
            OutputStream outputStream = crypto.getCipherOutputStream(
                    fileStream,
                    entity);

            // Write plaintext to it.
            outputStream.write(plainTextBytes);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CryptoInitializationException e) {
            e.printStackTrace();
        } catch (KeyChainException e) {
            e.printStackTrace();
        }
    }

    /**
     * decryptingContent
     *
     * @param file
     * @param entity
     * @param crypto
     * @param out
     */
    public void decryptingContent(File file, Entity entity, Crypto crypto, OutputStream out) {
        try {
            // Get the file to which ciphertext has been written.
            FileInputStream fileStream = new FileInputStream(file);

            // Creates an input stream which decrypts the data as
            // it is read from it.
            InputStream inputStream = crypto.getCipherInputStream(
                    fileStream,
                    entity);

            // Read into a byte array.
            int read;
            byte[] buffer = new byte[1024];

            // You must read the entire stream to completion.
            // The verification is done at the end of the stream.
            // Thus not reading till the end of the stream will cause
            // a security bug.
            while ((read = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }

            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CryptoInitializationException e) {
            e.printStackTrace();
        } catch (KeyChainException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] getCipherKey() throws KeyChainException {
        return new byte[0];
    }

    @Override
    public byte[] getMacKey() throws KeyChainException {
        return new byte[0];
    }

    @Override
    public byte[] getNewIV() throws KeyChainException {
        return new byte[0];
    }

    @Override
    public void destroyKeys() {

    }
}

