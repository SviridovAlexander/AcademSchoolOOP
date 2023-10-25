package com.asviridov.academit.temperature.scales;

public interface TemperatureConverter {
    String getName();

    double convertToCelsiusScale(double temperature);

    double convertFromCelsiusScale(double temperature);
}
