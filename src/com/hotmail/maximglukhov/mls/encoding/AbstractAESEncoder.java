package com.hotmail.maximglukhov.mls.encoding;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.Key;

/**
 * Created by maxim on 1/20/2018.
 */
public abstract class AbstractAESEncoder implements MLSEncoder {

    private String algorithm;

    private Cipher cipherInstance;

    public AbstractAESEncoder() {
        algorithm = getAlgorithm();

        try {
            cipherInstance = Cipher.getInstance(algorithm);
            cipherInstance.init(Cipher.ENCRYPT_MODE, generateKey(algorithm));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Cipher getCipherInstance() {
        return cipherInstance;
    }

    protected abstract Key generateKey(String algorithm);
}
