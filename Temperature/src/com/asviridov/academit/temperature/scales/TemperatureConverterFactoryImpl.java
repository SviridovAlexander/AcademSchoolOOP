package com.asviridov.academit.temperature.scales;

import java.util.List;

public class TemperatureConverterFactoryImpl implements TemperatureConverterFactory {
    private static final String CELSIUS = "Celsius";
    private static final String FAHRENHEIT = "Fahrenheit";
    private static final String KELVIN = "Kelvin";

    @Override
    public List<String> getSupportedScales() {
        return List.of(CELSIUS, FAHRENHEIT, KELVIN);
    }

    @Override
    public TemperatureConverter getTemperatureConverter(String scale) {
        return switch (scale) {
            case CELSIUS -> new CelsiusConverter();
            case FAHRENHEIT -> new FahrenheitConverter();
            case KELVIN -> new KelvinConverter();
            default -> throw new IllegalArgumentException("Unsupported temperature scale: " + scale);
        };
    }
}
