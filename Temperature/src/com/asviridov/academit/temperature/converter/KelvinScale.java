package com.asviridov.academit.temperature.converter;

public class KelvinScale implements Scale {
    @Override
    public String toString() {
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
