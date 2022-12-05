package com.ssau.streamingservice.services;

import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageInputStreamImpl;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class StreamingService {

    public static void startWebcamStreaming(int port) throws IOException {

        ServerSocket serverSocket = new ServerSocket(port);

        Socket socket = serverSocket.accept();
        OutputStream outputStream = socket.getOutputStream();
        BufferedOutputStream outputStream1 = new BufferedOutputStream(outputStream);
        InputStream inputStream = socket.getInputStream();

        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(new Dimension(640, 480));
        webcam.open();

        while (true) {
            BufferedImage image = webcam.getImage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            byte[] imageBytes = baos.toByteArray();
            outputStream1.write(imageBytes);
            outputStream1.flush();
            inputStream.readAllBytes();
        }
    }
}
