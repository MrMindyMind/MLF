package com.hotmail.maximglukhov.mls.impl;

import com.hotmail.maximglukhov.mls.encoding.AbstractAESEncoder;
import sun.misc.BASE64Encoder;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Created by maxim on 1/20/2018.
 */
public class AESEncoderImpl extends AbstractAESEncoder {
    @Override
    public String encode(String layeredWord) {
        try {
            return new BASE64Encoder().encode(getCipherInstance().doFinal(layeredWord.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getDelimiter() {
        return " ";
    }

    @Override
    public String getAlgorithm() {
        return "AES";
    }

    @Override
    protected Key generateKey(String algorithm) {
        return new SecretKeySpec("TheBestSecretKey".getBytes(), algorithm);
    }
}
