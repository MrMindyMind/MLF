package com.hotmail.maximglukhov.mls;

import com.hotmail.maximglukhov.mls.decoding.MLSDecoder;
import com.hotmail.maximglukhov.mls.encoding.MLSEncoder;

/**
 * Created by maxim on 1/20/2018.
 */
public class MultiLayeredString {

    private int layerCount;
    private String[] layers;

    public static MultiLayeredString decode(String input, MLSDecoder decoder) {
        String[] encodedLayeredWords = input.split(decoder.getDelimiter());
        String layeredWord;
        String[] decodedLayers = null;
        boolean isSizeDetermined = false;
        for (String encodedLayeredWord : encodedLayeredWords) {
            if (encodedLayeredWord.trim().isEmpty()) {
                continue;
            }

            layeredWord = decoder.decode(encodedLayeredWord);
            int layers = layeredWord.length();
            if (!isSizeDetermined) {
                decodedLayers = new String[layers];
                for (int i = 0; i < layers; i++) {
                    decodedLayers[i] = "";
                }
                isSizeDetermined = true;
            }

            for (int i = 0; i < layers; i++) {
                decodedLayers[i] += layeredWord.charAt(i);
            }
        }

        MultiLayeredString decodedMLS = new MultiLayeredString(decodedLayers.length);
        System.arraycopy(decodedLayers, 0, decodedMLS.layers, 0, decodedLayers.length);

        return decodedMLS;
    }

    public MultiLayeredString(int layers) {
        layerCount = layers;
        this.layers = new String[layers];
    }

    public int getLayerCount() {
        return layerCount;
    }

    public String getLayer(int index) {
        return layers[index];
    }

    public void setLayer(int index, String layerString) {
        layers[index] = layerString;
    }

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

    private int layerMax() {
        int longestLayer = -1;
        for (String layer : layers) {
            longestLayer = Math.max(longestLayer, layer.length());
        }

        return longestLayer;
    }
}
