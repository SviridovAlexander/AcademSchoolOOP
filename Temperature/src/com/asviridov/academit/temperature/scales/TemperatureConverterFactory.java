package com.asviridov.academit.temperature.scales;

import java.util.List;

public interface TemperatureConverterFactory {
    List<String> getSupportedScales();
    TemperatureConverter getTemperatureConverter(String scale);
}