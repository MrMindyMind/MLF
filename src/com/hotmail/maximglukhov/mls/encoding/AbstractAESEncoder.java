package com.hotmail.maximglukhov.mls.encoding;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.Key;

/**
 /**
 * Abstract implementation for {@link MLSEncoder} using AES encryption.
 */
public abstract class AbstractAESEncoder implements MLSEncoder {

    /**
     * Algorithm representation of this decoder.
     */
    private String algorithm;

    /**
     * Holds instance of the {@link Cipher} used for encoding.
     */
    private Cipher cipherInstance;

    /**
     * Constructs a new AES based encoder.
     */
    public AbstractAESEncoder() {
        algorithm = getAlgorithm();

        try {
            cipherInstance = Cipher.getInstance(algorithm);
            cipherInstance.init(Cipher.ENCRYPT_MODE, generateKey(algorithm));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the {@link Cipher} used for this decoder.
     * @return {@link Cipher} used for this decoder.
     */
    protected Cipher getCipherInstance() {
        return cipherInstance;
    }

    /**
     * Generates key to use with this decoder.
     * @param algorithm algorithm string representation of the algorithm used by this decoder.
     * @return instance of {@link Key} implementation.
     */
    protected abstract Key generateKey(String algorithm);
}
