package com.hotmail.maximglukhov.mls.encoding;

/**
 * Encodes a {@link com.hotmail.maximglukhov.mls.MultiLayeredString}.
 */
public interface MLSEncoder {

    /**
     * <p>Encodes a layered word.</p>
     * <p>Each layered word is constructed of characters from all layers in ascending order.</p>
     * @param layeredWord layered word to encode.
     * @return encoded layered word.
     */
    String encode(String layeredWord);

    /**
     * Gets delimiter used by this encoder.
     * @return delimiter as {@link String}
     */
    String getDelimiter();

    /**
     * <p>Gets encoding algorithm represented as string.</p>
     * <p>This algorithm will be used to encode the layered word.</p>
     * @return algorithm as {@link String}.
     */
    String getAlgorithm();
}
