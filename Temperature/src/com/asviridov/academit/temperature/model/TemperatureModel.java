package com.asviridov.academit.temperature.model;

import com.asviridov.academit.temperature.scales.TemperatureConverterFactory;

public class TemperatureModel {
    private double result;

    private final TemperatureConverterFactory factory;

    public TemperatureModel(TemperatureConverterFactory factory) {
        this.factory = factory;
    }

    public void updateModel(String fromScale, String toScale, double temperature) {
        if (fromScale.equals(toScale)) {
            result = temperature;
        } else if (fromScale.equals(TemperatureConverterFactory.CELSIUS)) {
            result = factory.getTemperatureConverter(toScale).convertFromCelsiusScale(temperature);
        } else {
            result = factory.getTemperatureConverter(toScale).convertFromCelsiusScale(
                    factory.getTemperatureConverter(fromScale).convertToCelsiusScale(temperature)
            );
        }
    }

    public double getResult() {
        return result;
    }
}
