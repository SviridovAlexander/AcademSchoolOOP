package com.asviridov.academit.temperature.scales;

import java.util.HashMap;
import java.util.Map;

public class TemperatureConverterFactoryImpl implements TemperatureConverterFactory {
    Map<String, TemperatureConverter> converterMap = new HashMap<>();

    @Override
    public TemperatureConverter getTemperatureConverter(String scale) {
        return converterMap.computeIfAbsent(
                scale,
                (s) -> switch (s) {
                    case CELSIUS -> new CelsiusConverter();
                    case FAHRENHEIT -> new FahrenheitConverter();
                    case KELVIN -> new KelvinConverter();
                    default -> throw new IllegalArgumentException("Unsupported temperature scale: " + scale);
                }
        );
    }
}
