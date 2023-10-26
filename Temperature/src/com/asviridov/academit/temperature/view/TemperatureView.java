package com.asviridov.academit.temperature.view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Vector;

public class TemperatureView implements View {
    private final JTextField inputField;
    private final JComboBox<String> fromScaleComboBox;
    private final JComboBox<String> toScaleComboBox;
    private final JButton convertButton;
    private final JLabel resultLabel;
    private final JFrame frame;

    public TemperatureView(java.util.List<String> supportedScales) {
        frame = new JFrame("Temperature Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLayout(new FlowLayout());

        inputField = new JTextField(10);
        fromScaleComboBox = new JComboBox<>(new Vector<>(supportedScales));
        toScaleComboBox = new JComboBox<>(new Vector<>(supportedScales));
        convertButton = new JButton("Convert");
        resultLabel = new JLabel();
    }

    public void start() {
        SwingUtilities.invokeLater(() -> {
            frame.add(inputField);
            frame.add(fromScaleComboBox);
            frame.add(toScaleComboBox);
            frame.add(convertButton);
            frame.add(resultLabel);

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
            frame.setVisible(true);
        });
    }

    protected void processInputTemperature(String input) {
        resultLabel.setText(null);

        try {
            Double.parseDouble(input);
            convertButton.setEnabled(true);
        } catch (NumberFormatException ex) {
            convertButton.setEnabled(false);
        }
    }

    public double getInputTemperature() {
        return Double.parseDouble(inputField.getText());
    }

    public String getFromScale() {
        return Objects.requireNonNull(fromScaleComboBox.getSelectedItem()).toString();
    }

    public String getToScale() {
        return Objects.requireNonNull(toScaleComboBox.getSelectedItem()).toString();
    }

    public void setResultText(String resultText) {
        resultLabel.setText("Result: " + resultText);
    }

    public void addConvertButtonListener(ActionListener listener) {
        convertButton.addActionListener(listener);
    }
}