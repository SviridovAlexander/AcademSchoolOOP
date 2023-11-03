package com.asviridov.academit.temperature.converter;

public interface Scale {
    String toString();

    double convertToCelsiusScale(double temperature);

    double convertFromCelsiusScale(double temperature);
}
