package com.hotmail.maximglukhov.mls;

import com.hotmail.maximglukhov.mls.decoding.MLSDecoder;
import com.hotmail.maximglukhov.mls.encoding.MLSEncoder;

import java.util.Arrays;

/**
 * Holds a multi-layered string.
 */
public class MultiLayeredString {

    /**
     * Holds the amount of layers held.
     */
    private int layerCount;
    /**
     * Holds all string layer references within an array.
     */
    private String[] layers;

    /**
     * Decodes given input to a {@link MultiLayeredString}. Input format must be familiar with the decoder algorithm
     * and delimiter.
     * @param input input to decode.
     * @param decoder decoder to use.
     * @return decoded multi-layered-string as {@link MultiLayeredString}.
     */
    public static MultiLayeredString decode(String input, MLSDecoder decoder) {
        // Find all encoded words, organized as layers.
        String[] encodedLayeredWords = input.split(decoder.getDelimiter());
        String layeredWord;
        String[] decodedLayers = null;
        boolean isSizeDetermined = false;
        for (String encodedLayeredWord : encodedLayeredWords) {
            // Validate encoded word.
            if (encodedLayeredWord.trim().isEmpty()) {
                continue;
            }

            layeredWord = decoder.decode(encodedLayeredWord);
            int layers = layeredWord.length();
            if (!isSizeDetermined) {
                // Encoded string should have the same amount of layers for all words.
                decodedLayers = new String[layers];
                // Initialize decoded layers.
                Arrays.fill(decodedLayers, "");
                isSizeDetermined = true;
            }

            // Build the decoded layer.
            for (int i = 0; i < layers; i++) {
                decodedLayers[i] += layeredWord.charAt(i);
            }
        }

        MultiLayeredString decodedMLS = new MultiLayeredString(decodedLayers.length);
        System.arraycopy(decodedLayers, 0, decodedMLS.layers, 0, decodedLayers.length);

        return decodedMLS;
    }

    /**
     * Constructs new multi-layered-string with given amount of layers.
     * @param layers amount of layers this string holds.
     */
    public MultiLayeredString(int layers) {
        layerCount = layers;
        this.layers = new String[layers];
    }

    /**
     * Gets the amount of layers held by this multi-layered-string.
     * @return layer count.
     */
    public int getLayerCount() {
        return layerCount;
    }

    /**
     * Gets a string for a particular layer.
     * @param index index of layer to get string.
     * @return string at given layer.
     */
    public String getLayer(int index) {
        return layers[index];
    }

    /**
     * Sets a string for a particular layer.
     * @param index index of layer to set string.
     * @param layerString string for given layer.
     */
    public void setLayer(int index, String layerString) {
        layers[index] = layerString;
    }

    /**
     * Encodes this multi-layered-string to a single encoded strings, separated by {@link MLSDecoder#getDelimiter()}.
     * @param encoder encoder to use.
     * @return encoded string.
     */
    public String encode(MLSEncoder encoder) {
        int longestLayer = layerMax();
        StringBuilder layeredWord;
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < longestLayer; i++) {
            layeredWord = new StringBuilder();
            for (String layer : layers) {
                if (i >= layer.length()) {
                    layeredWord.append('\0');
                    continue;
                }

                layeredWord.append(layer.charAt(i));
            }

            encoded.append(encoder.encode(layeredWord.toString())).append(encoder.getDelimiter());
        }

        return encoded.toString();
    }

    /**
     * Calculates the longest layer.
     * @return longest layer.
     */
    private int layerMax() {
        int longestLayer = -1;
        for (String layer : layers) {
            longestLayer = Math.max(longestLayer, layer.length());
        }

        return longestLayer;
    }
}
