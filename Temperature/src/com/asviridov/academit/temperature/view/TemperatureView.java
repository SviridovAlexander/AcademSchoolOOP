package com.asviridov.academit.temperature.view;

import com.asviridov.academit.temperature.converter.Converter;
import com.asviridov.academit.temperature.converter.Scale;
import com.asviridov.academit.temperature.converter.TemperatureConverter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class TemperatureView implements View {
    private JTextField inputField;
    private JComboBox<Scale> fromScaleComboBox;
    private JComboBox<Scale> toScaleComboBox;
    private JLabel resultLabel;
    private JFrame frame;
    private final Converter converter;
    private final JButton convertButton = new JButton("Convert");
    public TemperatureView(TemperatureConverter converter) {
        this.converter = converter;
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Temperature Converter");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setMinimumSize(new Dimension(600, 300));

            inputField = new JTextField();
            fromScaleComboBox = new JComboBox<>();
            toScaleComboBox = new JComboBox<>();
            resultLabel = new JLabel();

            JPanel contentPane = new JPanel(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new FlowLayout());
            inputField = new JTextField(10);
            inputPanel.add(new JLabel("Enter Temperature:"));
            inputPanel.add(inputField);
            inputPanel.add(convertButton);

            JPanel scalePanel = new JPanel();
            scalePanel.setLayout(new FlowLayout());
            fromScaleComboBox = new JComboBox<>(converter.getTemperatureScales());
            toScaleComboBox = new JComboBox<>(converter.getTemperatureScales());
            scalePanel.add(new JLabel("From Scale:"));
            scalePanel.add(fromScaleComboBox);
            scalePanel.add(new JLabel("To Scale:"));
            scalePanel.add(toScaleComboBox);

            JPanel resultPanel = new JPanel();
            resultPanel.setLayout(new FlowLayout());
            resultLabel = new JLabel();
            resultPanel.add(resultLabel);

            inputField.setToolTipText("Enter the temperature to convert");
            fromScaleComboBox.setToolTipText("Select the source temperature scale");
            toScaleComboBox.setToolTipText("Select the target temperature scale");

            // Input is empty, so the button is initially disabled
            convertButton.setEnabled(false);

            // Add a handler watching the input field to enable/disable the button
            inputField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    processInputTemperature(inputField.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    processInputTemperature(inputField.getText());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    processInputTemperature(inputField.getText());
                }
            });

            c.gridx = 0;
            c.gridy = 0;
            c.anchor = GridBagConstraints.PAGE_START;
            contentPane.add(inputPanel, c);

            c.gridy = 1;
            contentPane.add(scalePanel, c);

            c.gridy = 2;
            c.anchor = GridBagConstraints.CENTER;
            contentPane.add(resultPanel, c);

            frame.setContentPane(contentPane);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }


    @Override
    public void processInputTemperature(String input) {
        resultLabel.setText(null);

        try {
            Double.parseDouble(input);
            convertButton.setEnabled(true);
        } catch (NumberFormatException ex) {
            convertButton.setEnabled(false);
        }
    }

    @Override
    public double getInputTemperature() {
        return Double.parseDouble(inputField.getText());
    }

    @Override
    public Scale getFromScale() {
        return fromScaleComboBox.getItemAt(toScaleComboBox.getSelectedIndex());
    }

    @Override
    public Scale getToScale() {
        return toScaleComboBox.getItemAt(fromScaleComboBox.getSelectedIndex());
    }

    @Override
    public void setResultText(String resultText) {
        resultLabel.setText("Result: " + resultText);
    }

    @Override
    public void addConvertButtonListener(ActionListener listener) {
        convertButton.addActionListener(listener);
    }
}