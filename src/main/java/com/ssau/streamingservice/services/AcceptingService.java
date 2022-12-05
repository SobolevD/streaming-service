package com.ssau.streamingservice.services;

import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class AcceptingService {

    public static void startAccept(int port) throws IOException {

        Socket socket = new Socket("127.0.0.1", port);

        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        int iteration = 0;
        while (true) {
            BufferedInputStream inputStream1 = new BufferedInputStream(inputStream);
            byte[] bytes = inputStream1.readAllBytes();
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            BufferedImage bufferedImage = ImageIO.read(bais);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            File outputfile = new File("image" + iteration++ + ".jpg");
            ImageIO.write(bufferedImage, "jpg", outputfile);

            outputStream.write("ok".getBytes());
            outputStream.flush();
        }
    }
}
