package com.ssau.streamingservice.services;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public abstract class AcceptingService {

    private static final String FILE_PATH = "tmp\\";
    private static final int FRAMES_COUNT = 200;

    public static void startAccept(int port) throws IOException {

        Socket socket = new Socket("127.0.0.1", port);

        try (DataInputStream rcv = new DataInputStream(new BufferedInputStream(socket.getInputStream()))) //never use DataStreams without buffering, too slow
        {
            int iteration = 0;
            for (int currentFrameNum = 0; currentFrameNum < FRAMES_COUNT; ++currentFrameNum) {
                int frameWidth = rcv.readInt();     //read image with
                int frameHeight = rcv.readInt();    //read image height

                int[] pixelData = new int[frameWidth * frameHeight];
                for (int i = 0; i < pixelData.length; i++) {
                    pixelData[i] = rcv.readInt();   //read pixel data
                }

                BufferedImage frame = new BufferedImage(frameWidth, frameHeight, BufferedImage.TYPE_INT_RGB); //create image
                frame.setRGB(0, 0, frameWidth, frameHeight, pixelData, 0, frameWidth);  //set pixel data
                File outputFile = new File(FILE_PATH + "image" + iteration++ + ".jpg");
                ImageIO.write(frame, "jpg", outputFile);
            }
        }
    }
}
