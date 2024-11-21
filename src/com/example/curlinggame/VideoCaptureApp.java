package com.example.curlinggame;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;

import java.lang.reflect.Field;
import org.opencv.core.Core;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class VideoCaptureApp {

    private JFrame frame;
    private JLabel imageLabel;
    private VideoCapture camera;
    private boolean isRecording;

    public VideoCaptureApp() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        
        camera = new VideoCapture(0);
        if (!camera.isOpened()) {
            System.out.println("Error: Could not open camera");
            return;
        }
        
        
        frame = new JFrame("Live Stream");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        frame.setLayout(new BorderLayout());
        
       
        imageLabel = new JLabel();
        frame.add(imageLabel, BorderLayout.CENTER);
        
       
        JButton stopButton = new JButton("Stop Recording");
        frame.add(stopButton, BorderLayout.SOUTH);
        
        
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isRecording = false;
            }
        });

   
        frame.setVisible(true);
        
     
        isRecording = true;
        startCamera();
    }
    
    private void startCamera() {
        new Thread(() -> {
            Mat frameMat = new Mat();
            while (isRecording) {
                if (camera.read(frameMat)) {
                    
                    Imgproc.resize(frameMat, frameMat, new Size(640, 480));
                    
                    
                    ImageIcon image = new ImageIcon(matToBufferedImage(frameMat));
                    imageLabel.setIcon(image);
                    imageLabel.repaint();
                }
            }
            camera.release();
            frame.dispose(); 
        }).start();
    }
    
    private BufferedImage matToBufferedImage(Mat mat) {
        int width = mat.width();
        int height = mat.height();
        int channels = mat.channels();
        
        byte[] sourcePixels = new byte[width * height * channels];
        mat.get(0, 0, sourcePixels);
        
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        image.getRaster().setDataElements(0, 0, width, height, sourcePixels);
        
        return image;
    }

    public static void main(String[] args) {
        System.setProperty("java.library.path", "C:\\opencv\\opencv\\build\\java\\x64"); // Not necessary if VM args are set
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        SwingUtilities.invokeLater(VideoCaptureApp::new);
    }


}
