package com.asviridov.academit.temperature.converter;

public class FahrenheitScale implements Scale {
    @Override
    public String toString() {
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
