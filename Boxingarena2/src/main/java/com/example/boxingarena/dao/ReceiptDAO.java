package com.example.boxingarena.dao;

import com.example.boxingarena.entity.Receipt;
import com.example.boxingarena.exception.DuplicateReceiptException;
import com.example.boxingarena.exception.ReceiptNotFoundException;
import com.opencsv.exceptions.CsvValidationException;


import java.io.IOException;
import java.sql.SQLException;


public abstract class ReceiptDAO {
    public abstract void createReceipt(Receipt receipt) throws SQLException, IOException, DuplicateReceiptException, ReceiptNotFoundException;

    public abstract Receipt retrieveReceipt(int userId) throws SQLException, ReceiptNotFoundException, CsvValidationException, IOException;

}
