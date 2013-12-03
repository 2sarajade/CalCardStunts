import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Shreyas Chand
 */
public class GUI extends JFrame{

    private CalStuntsCreator controller;
    private JPanel stuntPanel;

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

        mainPanel.add(stuntPanel);
        mainPanel.add(buttonPanel);

        return mainPanel;
    }

    private void displayNewStunt(Stunt stunt) {
        final ImageIcon imageIcon = new ImageIcon(stunt.getImage());
        final JLabel image = new JLabel(imageIcon);
        stuntPanel.add(image);
        this.validate();
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
                    final Stunt stunt = controller.addStunt(file);
                    displayNewStunt(stunt);
                }
            }
        }
    }
}