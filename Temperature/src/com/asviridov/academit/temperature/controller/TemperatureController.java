package com.asviridov.academit.temperature.controller;

import com.asviridov.academit.temperature.model.TemperatureModel;
import com.asviridov.academit.temperature.view.TemperatureView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureController {
    private final TemperatureModel model;
    private final TemperatureView view;

    public TemperatureController(TemperatureModel model, TemperatureView view) {
        this.model = model;
        this.view = view;

        // Add action listener for the conversion button
        view.addConvertButtonListener(new ConvertButtonListener());
    }

    class ConvertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get user input from the view
            double inputTemperature = view.getInputTemperature();
            String fromScale = view.getFromScale();
            String toScale = view.getToScale();

            // Update the model
            model.setTemperature(inputTemperature);
            model.setFromScale(fromScale);
            model.setToScale(toScale);
            model.setConvertors();

            // Update the view with the result
            view.setResultText(String.format("%.2f %s", model.getResult(), view.getToScale()));
        }
    }
}