package com.asviridov.academit.temperature.controller;

import com.asviridov.academit.temperature.converter.Converter;
import com.asviridov.academit.temperature.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureController implements Controller {
    private Converter model;
    private View view;

    public TemperatureController() {
    }

    @Override
    public void setView(View view) {
        this.view = view;
        view.setConvertButtonListener(new ConvertButtonListener());
    }

    @Override
    public void setConverter(Converter converter) {
        this.model = converter;
    }

    public class ConvertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Update the view with the result
            view.setResultText(String.format("%.2f %s", model.getResult(
                    view.getFromScale(),
                    view.getToScale(),
                    view.getInputTemperature()
            ), view.getFromScale()));
        }
    }
}