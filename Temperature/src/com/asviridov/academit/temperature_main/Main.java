package com.asviridov.academit.temperature_main;

import com.asviridov.academit.temperature.controller.TemperatureController;
import com.asviridov.academit.temperature.converter.*;
import com.asviridov.academit.temperature.view.TemperatureView;

public class Main {
    public static void main(String[] args) {
        Scale[] scales = new Scale[]{
                new CelsiusScale(),
                new KelvinScale(),
                new FahrenheitScale()
        };

        TemperatureConverter converter = new TemperatureConverter(scales);
        TemperatureView view = new TemperatureView(converter);
        TemperatureController controller = new TemperatureController();

        controller.setView(view);
        controller.setConverter(converter);
        view.start();
    }
}
