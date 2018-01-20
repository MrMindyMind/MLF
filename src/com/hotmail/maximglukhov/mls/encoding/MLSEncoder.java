package com.hotmail.maximglukhov.mls.encoding;

/**
 * Created by maxim on 1/20/2018.
 */
public interface MLSEncoder {

    String encode(String layeredWord);

    String getDelimiter();

    String getAlgorithm();
}
