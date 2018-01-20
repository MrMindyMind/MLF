package com.hotmail.maximglukhov.mls.decoding;

import javax.crypto.Cipher;
import java.security.Key;

/**
 * Created by maxim on 1/20/2018.
 */
public abstract class AbstractAESDecoder implements MLSDecoder {

    private String algorithm;

    private Cipher cipherInstance;

    public AbstractAESDecoder() {
        algorithm = getAlgorithm();

        try {
            cipherInstance = Cipher.getInstance(algorithm);
            cipherInstance.init(Cipher.DECRYPT_MODE, generateKey(algorithm));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Cipher getCipherInstance() {
        return cipherInstance;
    }

    protected abstract Key generateKey(String algorithm);
}
