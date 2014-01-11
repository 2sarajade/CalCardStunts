import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Shreyas Chand
 */
public class GUI extends JFrame{

    private CalStuntsCreator controller;
    private JPanel stuntPanel;

    private JButton moveLeftButton;
    private JButton moveRightButton;

    private Stunt selectedStunt;
    private JLabel selectedStuntLabel;


    public GUI(CalStuntsCreator controller) {
        this.controller = controller;
        setup();
    }

    private void setup() {
        getContentPane().add(prepareContents());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        setVisible(true);
    }

    private JPanel prepareContents() {
        final JPanel mainPanel = new JPanel();
        stuntPanel = new JPanel();
        final JPanel buttonPanel = new JPanel();

        final JButton addStuntButton = new JButton("Add Stunt");
        addStuntButton.addActionListener(new AddStuntListener());
        buttonPanel.add(addStuntButton);

        final JButton generateStuntButton = new JButton("Generate Stunt");
        generateStuntButton.addActionListener(new GenerateStuntListener());
        buttonPanel.add(generateStuntButton);

        moveLeftButton = new JButton("<-");
        moveLeftButton.addActionListener(new MoveListener());
        buttonPanel.add(moveLeftButton);

        final JButton deleteStuntButton= new JButton("Delete Stunt");
        deleteStuntButton.addActionListener(new DeleteListener());
        buttonPanel.add(deleteStuntButton);

        moveRightButton = new JButton("->");
        moveRightButton.addActionListener(new MoveListener());
        buttonPanel.add(moveRightButton);

        mainPanel.add(stuntPanel);
        mainPanel.add(buttonPanel);

        return mainPanel;
    }

    private void displayNewStunt(Stunt stunt) {
        final JLabel image = new JLabel(stunt);
        image.addMouseListener(new MouseClickListener());
        image.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        stuntPanel.add(image);
        this.validate();
    }

    private void refreshDisplayedStunts() {
        stuntPanel.removeAll();
        ArrayList<Stunt> stunts = controller.getAllStunts();
        for (Stunt stunt : stunts) {
            displayNewStunt(stunt);
        }
    }

    public void fileError() {
        System.out.println("Raising Awareness about File Errors");
    }

    private class AddStuntListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            final JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setMultiSelectionEnabled(true);
            final FileFilter filter = new FileNameExtensionFilter("Image Files", "png", "jpg", "bmp", "gif");
            fileChooser.setFileFilter(filter);

            final int choice = fileChooser.showOpenDialog(null);

            if (choice == JFileChooser.APPROVE_OPTION) {
                File[] files = fileChooser.getSelectedFiles();
                for(File file : files) {
                    Stunt stunt = null;
                    try {
                        stunt = controller.addStunt(file);
                    } catch (IOException e) {
                        fileError();
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
                    controller.saveStuntDirections(fileChooser.getSelectedFile());
                } catch (IOException e) {
                    fileError();
                }
            }
        }
    }

    private class MouseClickListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if (selectedStunt != null) {
                selectedStuntLabel.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
            }
            selectedStuntLabel = (JLabel) mouseEvent.getSource();
            selectedStuntLabel.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
            selectedStunt = (Stunt) selectedStuntLabel.getIcon();

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
            int currPosition = controller.getStuntPosition(selectedStunt);
            if (actionEvent.getSource() == moveLeftButton) {
                controller.setStuntPosition(selectedStunt, currPosition - 1);
            } else { // moveRightButton
                controller.setStuntPosition(selectedStunt, currPosition + 1);
            }
            refreshDisplayedStunts();
            GUI.this.validate();
        }
    }

    private class DeleteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (selectedStunt != null) {
                controller.removeStunt(selectedStunt);
                stuntPanel.remove(selectedStuntLabel);
                selectedStunt = null;
                selectedStuntLabel = null;
                GUI.this.validate();
            }
        }
    }
}