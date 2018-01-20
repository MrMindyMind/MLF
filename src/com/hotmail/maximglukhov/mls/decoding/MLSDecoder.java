package com.hotmail.maximglukhov.mls.decoding;

/**
 * Created by maxim on 1/20/2018.
 */
public interface MLSDecoder {

    String decode(String encodedLayeredWord);

    String getDelimiter();

    String getAlgorithm();
}
