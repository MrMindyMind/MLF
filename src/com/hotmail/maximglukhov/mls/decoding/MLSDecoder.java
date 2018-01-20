package com.hotmail.maximglukhov.mls.decoding;

/**
 * Created by maxim on 1/20/2018.
 */
public interface MLSDecoder {

    /**
     * <p>Decodes a layered word.</p>
     * <p>Each layered word is constructed of characters from all layers in ascending order.</p>
     * @param encodedLayeredWord encoded layered word to encode.
     * @return decoded layered word.
     */
    String decode(String encodedLayeredWord);

    /**
     * Gets delimiter used by this decoder (must match the one used by the decoder).
     * @return delimiter as {@link String}
     */
    String getDelimiter();

    /**
     * <p>Gets decoding algorithm represented as string.<br>It has to match the algorithm used to encode by a {@link com.hotmail.maximglukhov.mls.encoding.MLSEncoder}</p>
     * <p>This algorithm will be used to decode the layered word.</p>
     * @return algorithm as {@link String}.
     */
    String getAlgorithm();
}
