package com.shreyaschand.ucrc.stunts;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Shreyas Chand
 */
public class CalStuntsCreator extends JFrame {

    private StuntShow show;

    private JPanel stuntPanel;

    private JButton moveLeftButton;
    private JButton moveRightButton;

    private Stunt selectedStunt;

    public static void main(String[] args) {
        new CalStuntsCreator();
    }

    public CalStuntsCreator() {
        this.show = new StuntShow();
        setup();
    }

    private void setup() {
        getContentPane().add(prepareContents());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(640, 480);
        setVisible(true);
    }

    private JPanel prepareContents() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        stuntPanel = new JPanel();
        JPanel functionButtons = new JPanel();
        JPanel movementButtons = new JPanel();

        JButton addStuntButton = new JButton("Add Stunt");
        addStuntButton.addActionListener(new AddStuntListener());
        functionButtons.add(addStuntButton);

        JButton generateStuntButton = new JButton("Generate Stunt");
        generateStuntButton.addActionListener(new GenerateStuntListener());
        functionButtons.add(generateStuntButton);

        moveLeftButton = new JButton("<-");
        moveLeftButton.addActionListener(new MoveListener());
        movementButtons.add(moveLeftButton);

        JButton deleteStuntButton = new JButton("Delete Stunt");
        deleteStuntButton.addActionListener(new DeleteListener());
        movementButtons.add(deleteStuntButton);

        moveRightButton = new JButton("->");
        moveRightButton.addActionListener(new MoveListener());
        movementButtons.add(moveRightButton);

        mainPanel.add(stuntPanel, BorderLayout.CENTER);
        mainPanel.add(movementButtons, BorderLayout.SOUTH);
        mainPanel.add(functionButtons, BorderLayout.NORTH);

        return mainPanel;
    }

    public void showFileError(String errorMessage) {
        System.out.println("File Error");
        JOptionPane.showMessageDialog(this, errorMessage, "An Error Occurred", JOptionPane.ERROR_MESSAGE);
    }

    public Stunt addStunt(File stuntImageFile) throws IOException {
        BufferedImage stuntImage = ImageIO.read(stuntImageFile);

        Stunt stunt = new Stunt(stuntImage);
        show.addStunt(stunt);

        return stunt;
    }

    private void displayNewStunt(Stunt stunt) {
        stunt.addMouseListener(new MouseClickListener());
        stuntPanel.add(stunt);
        this.validate();
    }

    private void refreshDisplayedStunts() {
        stuntPanel.removeAll();
        ArrayList<Stunt> stunts = show.getStunts();
        for (Stunt stunt : stunts) {
            displayNewStunt(stunt);
        }
    }

    public void saveStuntDirections(File file) throws IOException {
        PrintWriter writer = new PrintWriter(file, "UTF-8");

        writer.print("Row_Number, Seat_Number" + ", ");
        StringBuilder headerString = new StringBuilder();
        for (int stuntNumber = 1; stuntNumber <= show.getNumOfStunts(); stuntNumber++) {
            headerString.append("Stunt_");
            headerString.append(stuntNumber);
            headerString.append("_Color, ");
        }
        writer.println(headerString.substring(0, headerString.length() - 2));
        writer.flush();

        for (int row = 0; row < show.getShowHeight(); row++) {
            for (int seat = 0; seat < show.getShowWidth(); seat++) {
                writer.print((row + 1) + ", " + (seat + 1) + ", ");
                StringBuilder stuntsString = new StringBuilder();
                for (int stunt = 0; stunt < show.getNumOfStunts(); stunt++) {
                    stuntsString.append(show.getSeatColor(stunt, row, seat));
                    stuntsString.append(", ");
                }
                writer.println(stuntsString.substring(0, stuntsString.length() - 2));
                writer.flush();
            }
        }
        writer.close();
    }

    private class AddStuntListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setMultiSelectionEnabled(true);
            FileFilter filter = new FileNameExtensionFilter("Image Files", "png", "jpg", "bmp", "gif");
            fileChooser.setFileFilter(filter);

            int choice = fileChooser.showOpenDialog(null);

            if (choice == JFileChooser.APPROVE_OPTION) {
                File[] files = fileChooser.getSelectedFiles();
                for (File file : files) {
                    Stunt stunt = null;
                    try {
                        stunt = CalStuntsCreator.this.addStunt(file);
                    } catch (IOException e) {
                        showFileError("Whoops! I couldn't open " + file.getName() + ". Maybe something is wrong with it.");
                    }
                    displayNewStunt(stunt);
                }
            }
        }

    }

    private class GenerateStuntListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JFileChooser fileChooser = new JFileChooser();

            int choice = fileChooser.showSaveDialog(null);
            if (choice == JFileChooser.APPROVE_OPTION) {
                try {
                    saveStuntDirections(fileChooser.getSelectedFile());
                } catch (IOException e) {
                    showFileError("Uh oh! I couldn't save that file.");
                }
            }
        }

    }

    private class MouseClickListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if (selectedStunt != null) {
                selectedStunt.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
            }
            selectedStunt = (Stunt) mouseEvent.getSource();
            selectedStunt.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));

        }

        @Override
        public void mousePressed(MouseEvent mouseEvent) {}

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {}

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {}

        @Override
        public void mouseExited(MouseEvent mouseEvent) {}

    }

    private class MoveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (selectedStunt == null) {
                return;
            }
            int currPosition = show.getStuntPosition(selectedStunt);
            if (actionEvent.getSource() == moveLeftButton) {
                show.setStuntPosition(selectedStunt, currPosition - 1);
            } else if (actionEvent.getSource() == moveRightButton) {
                show.setStuntPosition(selectedStunt, currPosition + 1);
            }
            refreshDisplayedStunts();
            CalStuntsCreator.this.validate();
        }
    }

    private class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (selectedStunt != null) {
                show.removeStunt(selectedStunt);
                stuntPanel.remove(selectedStunt);
                selectedStunt = null;
                CalStuntsCreator.this.validate();
            }
        }
    }
}