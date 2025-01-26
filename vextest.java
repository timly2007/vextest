package com.example.demo1;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.File;


public class vextest extends Application {

    final ImageView selectedImage = new ImageView();
    File file = new File("/Users/timothyweiyily/Desktop/Projects/demo1/images/WechatIMG206.png");
    Image image1 = new Image(file.toURI().toString(), 316, 0, true, true);

    int imageWidth = (int) image1.getWidth();
    int imageHeight = (int) image1.getHeight();

    int pixelColor[][] = new int[imageWidth][imageHeight];
    int pixelR[][] = new int[imageWidth][imageHeight];
    int pixelG[][] = new int[imageWidth][imageHeight];
    int pixelB[][] = new int[imageWidth][imageHeight];
    int pixelBrightness[][] = new int[imageWidth][imageHeight];

    // round 1 variables
    int pixelR2[][] = new int[imageWidth][imageHeight];
    int pixelG2[][] = new int[imageWidth][imageHeight];
    int pixelB2[][] = new int[imageWidth][imageHeight];
    int edgeCheckV = 0;
    int finalColorV = 0;
    int edgeCheckH = 0;
    int finalColorH = 0;
    int sensitivity = 20;
    Rectangle[][] imagePixel = new Rectangle[imageWidth][imageHeight];

    Rectangle[][] imagePixel1 = new Rectangle[imageWidth][imageHeight];

    // round 2
    int pixelR3[][] = new int[imageWidth][imageHeight];
    int pixelG3[][] = new int[imageWidth][imageHeight];
    int pixelB3[][] = new int[imageWidth][imageHeight];
    int edgeCheckV2 = 0;
    int finalColorV2 = 0;
    int sensitivity2 = 5400;
    Rectangle[][] imagePixel2 = new Rectangle[imageWidth][imageHeight];

    // round 3 (final)
    int pixelR4[][] = new int[imageWidth][imageHeight];
    int pixelG4[][] = new int[imageWidth][imageHeight];
    int pixelB4[][] = new int[imageWidth][imageHeight];
    int edgeCheckV3 = 0;
    int finalColorV3 = 0;
    int sensitivity3 = 30;
    Rectangle[][] imagePixel3 = new Rectangle[imageWidth][imageHeight];

    // kernel for scanning
    int[] kernel1 = {-2, 0, 0, 0, 2,
                    -2, 0, 0, 0, 2,
                    -2, 0, 0, 0, 2,
                    -2, 0, 0, 0, 2,
                    -2, 0, 0, 0, 2};
    int[] kernel2 = {2, 2, 2, 2, 2,
                     1, 1, 1, 1, 1,
                     0, 0, 0, 0, 0,
                     -1, -1, -1, -1, -1,
                     -2, -2, -2, -2, -2};
    int[] kernel3 = {3, 2, 0, -8, 0, 2, 3,
                     3, 2, 0, -8, 0, 2, 3,
                     2, 2, 0, -8, 0, 2, 2,
                     2, 2, 0, -8, 0, 2, 2,
                     2, 2, 0, -8, 0, 2, 2,
                     3, 2, 0, -8, 0, 2, 3,
                     3, 2, 0, -8, 0, 2, 3};

    int[] kernel4 = {0, 3, 4, 3, 0,
                     0, 0, 0, 0, 0,
                     0, 0, 0, 0, 0,
                     0, 0, 0, 0, 0,
                     0, 3, 4, 3, 0};
    public int findV(int x, int y) {
        int finalV = 0;
        int finalR = 0;
        int finalG = 0;
        int finalB = 0;

        for (int j = 0; j <= 4; j++) {
            for (int k = 0; k <= 4; k++) {
                finalR += pixelR[x + k - 2][y + j - 2] * kernel1[j * 5 + k];
                finalB += pixelB[x + k - 2][y + j - 2] * kernel1[j * 5 + k];
                finalG += pixelG[x + k - 2][y + j - 2] * kernel1[j * 5 + k];
            }
        }

        finalV = Math.abs(finalR) + Math.abs(finalG) + Math.abs(finalB);

        return finalV;
    }

    public int findH(int x, int y) {
        int finalH = 0;
        int finalR = 0;
        int finalG = 0;
        int finalB = 0;

        for (int j = 0; j <= 4; j++) {
            for (int k = 0; k <= 4; k++) {
                finalR += pixelR[x + k - 2][y + j - 2] * kernel2[j * 5 + k];
                finalB += pixelB[x + k - 2][y + j - 2] * kernel2[j * 5 + k];
                finalG += pixelG[x + k - 2][y + j - 2] * kernel2[j * 5 + k];
            }
        }

        finalH = Math.abs(finalR) + Math.abs(finalG) + Math.abs(finalB);

        return finalH;
    }

    public int findV2(int x, int y) {
        int finalV = 0;
        int finalR = 0;
        int finalG = 0;
        int finalB = 0;
        int finalR2 = 0;
        int finalG2 = 0;
        int finalB2 = 0;

        for (int j = 0; j <= 6; j++) {
            finalR = 0;
            finalG = 0;
            finalB = 0;

            for (int k = 0; k <= 6; k++) {
                finalR += pixelR2[x + j - 2][y + k - 2] * kernel3[k * 7 + j];
                finalB += pixelB2[x + j - 2][y + k - 2] * kernel3[k * 7 + j];
                finalG += pixelG2[x + j - 2][y + k - 2] * kernel3[k * 7 + j];
            }

            // raise to power
            if (finalR > 0) {
                finalR2 += (int) Math.pow(finalR, 1.5);
            }
            if (finalG > 0) {
                finalG2 += (int) Math.pow(finalG, 1.5);
            }
            if (finalB > 0) {
                finalB2 += (int) Math.pow(finalB, 1.5);
            }
        }

        finalV = Math.abs(finalR2) + Math.abs(finalG2) + Math.abs(finalB2);

        return finalV;
    }

    public int findV3(int x, int y) {
        int finalV = 0;
        int finalR = 0;
        int finalG = 0;
        int finalB = 0;

        for (int j = 0; j <= 4; j++) {
            for (int k = 0; k <= 4; k++) {
                finalR += pixelR3[x + k - 2][y + j - 2] * kernel4[j * 5 + k];
                finalB += pixelB3[x + k - 2][y + j - 2] * kernel4[j * 5 + k];
                finalG += pixelG3[x + k - 2][y + j - 2] * kernel4[j * 5 + k];
            }
        }

        finalV = Math.abs(finalR) + Math.abs(finalG) + Math.abs(finalB);

        return finalV;
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("VEX Robotics Test");
        stage.setWidth(1440);
        stage.setHeight(900);
        Scene scene = new Scene(new Group());
        StackPane root = new StackPane();

        System.out.println(imageWidth);
        System.out.println(imageHeight);
        Boolean printed = Boolean.FALSE;

        boolean detected = false;
        int totalX = 0;
        int totalY = 0;
        int total = 0;

        for (int i = 0; i < imageWidth; i++) {
            for (int j = 0; j < imageHeight; j++) {
                pixelColor[i][j] = image1.getPixelReader().getArgb(i, j);

                // Extracting RGB components
                pixelR[i][j] = (pixelColor[i][j] >> 16) & 0xFF;
                pixelG[i][j] = (pixelColor[i][j] >> 8) & 0xFF;
                pixelB[i][j] = pixelColor[i][j] & 0xFF;

                if (!printed) {
                    // Printing the RGB components
                    System.out.println("Red: " + pixelR[1][1]);
                    System.out.println("Green: " + pixelG[1][1]);
                    System.out.println("Blue: " + pixelB[1][1]);
                    System.out.println("Brightness: " + pixelBrightness[1][1]);
                    printed = Boolean.TRUE;
                }

                pixelBrightness[i][j] = (int) ((pixelR[i][j] + pixelG[i][j] + pixelB[i][j]) / 3);

                if (pixelB[i][j] > 107 || pixelG[i][j] < 130 || pixelG[i][j] > 230 || pixelR[i][j] < 120 || pixelR[i][j] > 210) {
                    pixelR[i][j] = 0;
                    pixelG[i][j] = 0;
                    pixelB[i][j] = 0;
                }

                // setting up pixels
                imagePixel[i][j] = new Rectangle(1, 1);
                imagePixel[i][j].setFill(Color.WHITE);

                imagePixel[i][j].setTranslateX((int) (-imageWidth / 2) + i);
                imagePixel[i][j].setTranslateY((int) (-3 * imageHeight / 2) + j);

                imagePixel1[i][j] = new Rectangle(1, 1);
                imagePixel1[i][j].setFill(Color.WHITE);

                imagePixel1[i][j].setTranslateX((int) (-imageWidth / 2) + i);
                imagePixel1[i][j].setTranslateY((int) (imageHeight / 2) + j);

                imagePixel2[i][j] = new Rectangle(1, 1);
                imagePixel2[i][j].setFill(Color.WHITE);

                imagePixel2[i][j].setTranslateX((int) (imageWidth / 2) + i);
                imagePixel2[i][j].setTranslateY((int) (-3 * imageHeight / 2) + j);

                imagePixel3[i][j] = new Rectangle(1, 1);
                imagePixel3[i][j].setFill(Color.WHITE);

                imagePixel3[i][j].setTranslateX((int) (imageWidth / 2) + i);
                imagePixel3[i][j].setTranslateY((int) (-imageHeight / 2) + j);
            }
        }

        for (int i = 2; i < imageWidth - 2; i++) {
            for (int j = 2; j < imageHeight - 2; j++) {
                edgeCheckV = findV(i, j);
                finalColorV = (int) Math.max(Math.min((edgeCheckV / sensitivity), 255), 0);

                // horizontal scanning not used ATM
                // edgeCheckH = findH(i, j);
                // finalColorH = (int) Math.max(Math.min((edgeCheckH / sensitivity), 255), 0);

                if (finalColorV > 100) {
                    pixelB2[i][j] = 255;
                } else {
                    pixelB2[i][j] = 0;
                }

                pixelR2[i][j] = finalColorH;
                pixelG2[i][j] = 0;
            }
        }

        for (int i = 5; i < imageWidth - 5; i++) {
            for (int j = 5; j < imageHeight - 5; j++) {
                edgeCheckV2 = findV2(i, j);
                finalColorV2 = (int) Math.max(Math.min((edgeCheckV2 / sensitivity2), 255), 0);

                pixelR3[i][j] = 0;

                // filtering out if too low
                if (finalColorV2 > 100) {
                    pixelG3[i][j] = 255;
                } else {
                    pixelG3[i][j] = 0;
                }
                pixelB3[i][j] = 0;
            }
        }

        for (int i = 7; i < imageWidth - 7; i++) {
            for (int j = 7; j < imageHeight - 7; j++) {
                edgeCheckV3 = findV3(i, j);
                finalColorV3 = (int) Math.max(Math.min((edgeCheckV3 / sensitivity3), 255), 0);

                // filtering out if too low
                if (finalColorV3 > 100) {
                    pixelG4[i][j] = 255;
                    pixelB4[i][j] = 255;
                    pixelR4[i][j] = 255;
                    totalX += i;
                    totalY += j;
                    total += 1;
                    detected = true;
                } else {
                    pixelG4[i][j] = 0;
                    pixelR4[i][j] = 0;
                    pixelB4[i][j] = 0;
                }

            }
        }

        selectedImage.setImage(image1);

        root.getChildren().addAll(selectedImage);

        for (int i = 0; i < imageWidth; i++) {
            for (int j = 0; j < imageHeight; j++) {

                // recoloring (finding edges)
                imagePixel[i][j].setOpacity(1);
                imagePixel[i][j].setFill(Color.rgb(pixelR2[i][j], pixelG2[i][j], pixelB2[i][j]));
                root.getChildren().add(imagePixel[i][j]);

                // recoloring (color check)
                imagePixel1[i][j].setOpacity(1);
                imagePixel1[i][j].setFill(Color.rgb(pixelR[i][j], pixelG[i][j], pixelB[i][j]));
                root.getChildren().add(imagePixel1[i][j]);

                // recoloring (finding double edges)
                imagePixel2[i][j].setOpacity(1);
                imagePixel2[i][j].setFill(Color.rgb(pixelR3[i][j], pixelG3[i][j], pixelB3[i][j]));
                root.getChildren().add(imagePixel2[i][j]);

                // recoloring (isolating rods)
                imagePixel3[i][j].setOpacity(1);
                imagePixel3[i][j].setFill(Color.rgb(pixelR4[i][j], pixelG4[i][j], pixelB4[i][j]));
                root.getChildren().add(imagePixel3[i][j]);
            }
        }

        scene.setRoot(root);

        stage.setScene(scene);
        stage.show();

        if (detected) {
            System.out.println("Mobile goal detected: " + totalX / total + ", " + totalY / total);
        } else {
            System.out.println("no mobile goal detected");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}