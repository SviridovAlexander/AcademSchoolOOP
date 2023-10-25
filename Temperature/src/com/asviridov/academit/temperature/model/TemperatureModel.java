package com.asviridov.academit.temperature.model;

import com.asviridov.academit.temperature.scales.TemperatureConverter;
import com.asviridov.academit.temperature.scales.TemperatureConverterFactory;

public class TemperatureModel {
    private double temperature;
    private String fromScale;
    private String toScale;

    private TemperatureConverterFactory factory;

    private TemperatureConverter fromConverter;
    private TemperatureConverter toConverter;

    public TemperatureModel(TemperatureConverterFactory factory) {
        this.fromConverter = factory.getTemperatureConverter("Celsius"); // Default to Celsius
        this.toConverter = factory.getTemperatureConverter("Celsius"); // Default to Celsius
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setFromScale(String fromScale) {
        this.fromScale = fromScale;
    }

    public void setToScale(String toScale) {
        this.toScale = toScale;
    }

    public double getResult() {
        return toConverter.convertFromCelsiusScale(fromConverter.convertToCelsiusScale(temperature));
    }

    public void setConvertors() {
        this.fromConverter = factory.getTemperatureConverter(fromScale);
        this.toConverter = factory.getTemperatureConverter(toScale);
    }

    public void setConverterFactory(TemperatureConverterFactory factory) {
        this.factory = factory;
    }
}
