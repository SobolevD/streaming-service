package com.ssau.streamingservice.services;

import com.github.sarxos.webcam.Webcam;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;

public abstract class StreamingService {

    private static final int FRAMES_COUNT = 200;

    public static void startWebcamStreaming(int port) throws IOException {

        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(new Dimension(640, 480));
        webcam.open();

        ServerSocket serverSocket = new ServerSocket(port);

        try (DataOutputStream sender = new DataOutputStream(new BufferedOutputStream(serverSocket.accept().getOutputStream())))
        {
            for (int currentFrameNum = 0; currentFrameNum < FRAMES_COUNT; ++currentFrameNum)
            {
                BufferedImage frame = webcam.getImage();
                int frameWidth = frame.getWidth();
                int frameHeight = frame.getHeight();

                sender.writeInt(frameWidth);    //write image with
                sender.writeInt(frameHeight);   //write image height

                int[] pixelData = new int[frameWidth * frameHeight];
                frame.getRGB(0, 0, frameWidth, frameHeight, pixelData, 0, frameWidth);

                for (int pixelDatum : pixelData) {
                    sender.writeInt(pixelDatum);
                }
            }
        }
    }
}
