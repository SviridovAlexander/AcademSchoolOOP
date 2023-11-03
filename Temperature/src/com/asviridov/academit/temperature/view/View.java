package com.asviridov.academit.temperature.view;

import com.asviridov.academit.temperature.converter.Scale;

import java.awt.event.ActionListener;

public interface View {
    void start();

    void processInputTemperature(String input);

    double getInputTemperature();

    Scale getFromScale();

    Scale getToScale();

    void setResultText(String resultText);

    void setConvertButtonListener(ActionListener listener);
}
