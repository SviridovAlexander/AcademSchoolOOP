package com.asviridov.academit.temperature_main;

import com.asviridov.academit.temperature.controller.TemperatureController;
import com.asviridov.academit.temperature.model.TemperatureModel;
import com.asviridov.academit.temperature.scales.TemperatureConverterFactory;
import com.asviridov.academit.temperature.scales.TemperatureConverterFactoryImpl;
import com.asviridov.academit.temperature.view.TemperatureView;

public class Main {
    public static void main(String[] args) {
        TemperatureConverterFactory factory = new TemperatureConverterFactoryImpl();
        TemperatureModel model = new TemperatureModel(factory);
        TemperatureView view = new TemperatureView(factory.getSupportedScales());
        TemperatureController controller = new TemperatureController(model, view);

        view.start();
    }
}
