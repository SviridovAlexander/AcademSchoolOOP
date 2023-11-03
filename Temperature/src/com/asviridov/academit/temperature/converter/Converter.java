package com.asviridov.academit.temperature.converter;

public interface Converter {
    Scale[] getTemperatureScales();

    double getResult(Scale fromScale, Scale toScale, double temperature);
}
