package com.asviridov.academit.temperature.scales;

public class CelsiusConverter implements TemperatureConverter {
    @Override
    public String getName() {
        return "Celsius";
    }

    @Override
    public double convertToCelsiusScale(double temperature) {
        return temperature;
    }

    @Override
    public double convertFromCelsiusScale(double temperature) {
        return temperature;
    }
}