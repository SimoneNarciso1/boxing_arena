package com.example.boxingarena.graphic_controller;


import com.example.boxingarena.exception.InvalidFormatException;

import java.sql.SQLException;

public interface InitializableController2 {

    void initializeData2(Object data,  int id) throws InvalidFormatException, SQLException;

}
