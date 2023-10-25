package com.asviridov.academit.temperature.scales;

public class KelvinConverter implements TemperatureConverter {
    @Override
    public String getName() {
        return "Kelvin";
    }

    @Override
    public double convertToCelsiusScale(double temperature) {
        return temperature - 273.15;
    }

    @Override
    public double convertFromCelsiusScale(double temperature) {
        return temperature + 273.15;
    }
}
