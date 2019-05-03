package ourProject.insuranceCalc;

import javax.swing.*;

public class Window extends JFrame{
    public static final int WIDTH = 720;
    public static final int HEIGHT = 645;
    private static String title = "Расчет стоимости";
    private static int actionOnClose = 2;

    public static void main(String[] args) {
        GUI gui = new GUI();
        Window mainWindow = new Window();
        JPanel mainPanel = new Panel();
        gui.initWindow(mainWindow, WIDTH, HEIGHT, title, actionOnClose, mainPanel);
    }
}