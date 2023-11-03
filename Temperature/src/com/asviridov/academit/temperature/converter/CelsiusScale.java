package com.asviridov.academit.temperature.converter;

public class CelsiusScale implements Scale {
    @Override
    public String toString() {
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