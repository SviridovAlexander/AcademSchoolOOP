package com.asviridov.academit.temperature.scales;

import java.util.List;

public interface TemperatureConverterFactory {
    String CELSIUS = "Celsius";
    String FAHRENHEIT = "Fahrenheit";
    String KELVIN = "Kelvin";

    default List<String> getSupportedScales() {
        return List.of(CELSIUS, FAHRENHEIT, KELVIN);
    }

    TemperatureConverter getTemperatureConverter(String scale);
}