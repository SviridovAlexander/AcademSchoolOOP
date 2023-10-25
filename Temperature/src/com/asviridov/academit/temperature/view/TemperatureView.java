package com.asviridov.academit.temperature.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Vector;

public class TemperatureView {
    private final JFrame frame;
    private final JTextField inputField;
    private final JComboBox<String> fromScaleComboBox;
    private final JComboBox<String> toScaleComboBox;
    private final JButton convertButton;
    private final JLabel resultLabel;

    public TemperatureView(java.util.List<String> supportedScales) {
        frame = new JFrame("Temperature Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        inputField = new JTextField(10);
        fromScaleComboBox = new JComboBox<>(new Vector<>(supportedScales));
        toScaleComboBox = new JComboBox<>(new Vector<>(supportedScales));
        convertButton = new JButton("Convert");
        resultLabel = new JLabel("Result: ");

        frame.add(inputField);
        frame.add(fromScaleComboBox);
        frame.add(toScaleComboBox);
        frame.add(convertButton);
        frame.add(resultLabel);

        frame.setVisible(true);
    }

    public double getInputTemperature() {
        try {
            return Double.parseDouble(inputField.getText());
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
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

    public void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void addConvertButtonListener(ActionListener listener) {
        convertButton.addActionListener(listener);
    }
}

