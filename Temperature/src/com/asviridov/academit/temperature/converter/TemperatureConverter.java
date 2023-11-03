package com.asviridov.academit.temperature.converter;

public class TemperatureConverter implements Converter {
    private final Scale[] scales;

    public TemperatureConverter(Scale[] scales) {
        this.scales = scales;
    }

    @Override
    public Scale[] getTemperatureScales() {
        return scales;
    }

    @Override
    public double getResult(Scale fromScale, Scale toScale, double temperature) {
        return toScale.convertToCelsiusScale(fromScale.convertFromCelsiusScale(temperature));
    }
}
