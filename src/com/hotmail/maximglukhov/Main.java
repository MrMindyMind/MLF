package com.hotmail.maximglukhov;

import com.hotmail.maximglukhov.mls.MultiLayeredString;
import com.hotmail.maximglukhov.mls.impl.AESDecoderImpl;
import com.hotmail.maximglukhov.mls.impl.AESEncoderImpl;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;

public class Main {

    public static void main(String[] args) {
        testEncodingDecoding();

        testFileSaving();
    }

    private static void testFileSaving() {
        MultiLayeredString mls = new MultiLayeredString(5);
        mls.setLayer(0, "Sed a lacus eget purus tempor iaculis ac in urna. In iaculis risus id nibh gravida, vel luctus est ultricies.");
        mls.setLayer(1, "Vivamus tincidunt augue ut leo sodales eleifend. Donec pulvinar ultrices odio, at aliquam diam. Lorem.");
        mls.setLayer(2, "Sed at convallis mauris. Maecenas convallis ut eros vitae commodo. Lorem ipsum dolor.");
        mls.setLayer(3, "Aenean pellentesque fringilla orci ac molestie. Maecenas sit amet nibh ac sem finibus euismod. Pellentesque habitant morbi tristique senectus et netus et malesuada.");
        mls.setLayer(4, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam volutpat.");

        try {
            // Note: mlf extension stands for "multi layered file".
            Path path = Paths.get("tests.mlf");
            if (Files.exists(path))
                Files.delete(path);

            Files.write(path, mls.encode(new AESEncoderImpl()).getBytes(), StandardOpenOption.CREATE);
            System.out.println("decoded mlf:");
            System.out.println(MultiLayeredString.decode(new String(Files.readAllBytes(Paths.get("tests.mlf"))), new AESDecoderImpl()).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testEncodingDecoding() {
        String in = "test string";
        String encodedTestString = new AESEncoderImpl().encode(in);
        System.out.println("encoded=" + encodedTestString);
        System.out.println("decoded=" + new AESDecoderImpl().decode(encodedTestString));

        MultiLayeredString mls = new MultiLayeredString(5);
        mls.setLayer(0, "Sed a lacus eget purus tempor iaculis ac in urna. In iaculis risus id nibh gravida, vel luctus est ultricies.");
        mls.setLayer(1, "Vivamus tincidunt augue ut leo sodales eleifend. Donec pulvinar ultrices odio, at aliquam diam. Lorem.");
        mls.setLayer(2, "Sed at convallis mauris. Maecenas convallis ut eros vitae commodo. Lorem ipsum dolor.");
        mls.setLayer(3, "Aenean pellentesque fringilla orci ac molestie. Maecenas sit amet nibh ac sem finibus euismod. Pellentesque habitant morbi tristique senectus et netus et malesuada.");
        mls.setLayer(4, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam volutpat.");

        String encodedMLS = mls.encode(new AESEncoderImpl());
        System.out.println("encoded= " + encodedMLS);
        System.out.println("decoded layers:");
        MultiLayeredString decoded = MultiLayeredString.decode(encodedMLS, new AESDecoderImpl());
        for (int i = 0; i < decoded.getLayerCount(); i++) {
            System.out.println("" + i + ") " + decoded.getLayer(i));
        }
    }
}
