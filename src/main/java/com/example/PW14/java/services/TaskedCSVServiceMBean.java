package com.example.PW14.java.services;

import java.io.IOException;

public interface TaskedCSVServiceMBean {
    void writeData() throws IOException;
    void execute();
}