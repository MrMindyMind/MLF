package com.hotmail.maximglukhov.mls.decoding;

import javax.crypto.Cipher;
import java.security.Key;

/**
 * Abstract implementation for {@link MLSDecoder} using AES encryption.
 */
public abstract class AbstractAESDecoder implements MLSDecoder {

    /**
     * Algorithm representation of this decoder.
     */
    private String algorithm;

    /**
     * Holds instance of the {@link Cipher} used for decoding.
     */
    private Cipher cipherInstance;

    /**
     * Constructs a new AES based decoder.
     */
    public AbstractAESDecoder() {
        algorithm = getAlgorithm();

        try {
            cipherInstance = Cipher.getInstance(algorithm);
            cipherInstance.init(Cipher.DECRYPT_MODE, generateKey(algorithm));
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
