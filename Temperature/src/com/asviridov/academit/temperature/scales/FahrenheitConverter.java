package com.asviridov.academit.temperature.scales;

public class FahrenheitConverter implements TemperatureConverter {
    @Override
    public String getName() {
        return "Fahrenheit";
    }

    @Override
    public double convertToCelsiusScale(double temperature) {
        return (temperature - 32) * 5 / 9;
    }

    @Override
    public double convertFromCelsiusScale(double temperature) {
        return temperature * 1.8 + 32;
    }
}
