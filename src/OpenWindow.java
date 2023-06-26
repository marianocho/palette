import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

public class OpenWindow extends JFrame {
    private JPanel creatorPanel;
    private JPanel namePanel;
    private JTextField creatorField;
    private JTextField nameField;

    private JPanel panelButton;
    private JButton openButton;
    private JButton cancelButton;

    public OpenWindow(Window window) {
        super("Open Draw");

        this.setLayout(new GridLayout(3, 2));

        
        creatorPanel = new JPanel();
        creatorPanel.setLayout(new GridLayout(1, 0));

        creatorField = new JTextField(15);

        creatorPanel.add(new JLabel(Constants.CREATOR_LABEL));
        creatorPanel.add(creatorField);

        this.add(creatorPanel);

        namePanel = new JPanel();
        namePanel.setLayout(new GridLayout(1, 0));

        nameField = new JTextField(15);

        namePanel.add(new JLabel(Constants.NAME_LABEL));
        namePanel.add(nameField);

        this.add(namePanel);

        panelButton = new JPanel();
        panelButton.setLayout(new GridLayout(1, 2));

        openButton   = new JButton(Constants.OPEN_LABEL);
        cancelButton = new JButton(Constants.CANCEL_LABEL);

        panelButton.add(openButton);
        panelButton.add(cancelButton);

        this.add(panelButton);

        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.openDraw(creatorField.getText(), nameField.getText());
                OpenWindow.this.dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OpenWindow.this.dispose();
            }
        });

        this.setSize (300,150);
        this.setLocationRelativeTo(null);
        this.setVisible (true);
    }
}