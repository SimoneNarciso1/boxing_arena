package com.example.boxingarena.graphic_controller;

import com.example.boxingarena.exception.InvalidFormatException;

import java.sql.SQLException;

public interface InitializableController {
    void initializeData(Object data) throws InvalidFormatException, SQLException;


}
