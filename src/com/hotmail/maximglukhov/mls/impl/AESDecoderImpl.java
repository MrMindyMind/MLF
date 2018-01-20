package com.hotmail.maximglukhov.mls.impl;

import com.hotmail.maximglukhov.mls.decoding.AbstractAESDecoder;
import sun.misc.BASE64Decoder;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Created by maxim on 1/20/2018.
 */
public class AESDecoderImpl extends AbstractAESDecoder {

    @Override
    public String decode(String encodedLayeredWord) {
        try {
            return new String(getCipherInstance().doFinal(new BASE64Decoder().decodeBuffer(encodedLayeredWord)));
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
