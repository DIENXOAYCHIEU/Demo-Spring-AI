package com.ai.springaidemo.interfaces;

import java.util.Map;

public interface IImage{
    Map<String, Object> generate(String prompt);
}