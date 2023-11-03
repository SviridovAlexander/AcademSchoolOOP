package com.asviridov.academit.temperature.controller;

import com.asviridov.academit.temperature.converter.Converter;
import com.asviridov.academit.temperature.view.View;

public interface Controller {
    void setView(View view);
    void setConverter(Converter converter);
}
