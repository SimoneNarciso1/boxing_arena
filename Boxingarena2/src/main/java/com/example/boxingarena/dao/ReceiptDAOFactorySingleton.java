package com.example.boxingarena.dao;

import java.io.IOException;
import java.time.LocalDate;

public class ReceiptDAOFactorySingleton {

    private ReceiptDAOFactorySingleton() {

    }

    private static class SingletonHelper {
        private static final ReceiptDAOFactorySingleton INSTANCE = new ReceiptDAOFactorySingleton();

    }

    public static ReceiptDAOFactorySingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public ReceiptDAO createReceiptDAO() throws IllegalArgumentException, IOException {
        boolean factoryNumber = getPersistence();
        if(factoryNumber){
            return new ReceiptDAOJDBC();
        }

        return new ReceiptDAOCSV();

    }

    public static boolean getPersistence() {
        LocalDate date = LocalDate.now();
        int number = date.getDayOfMonth() % 2;
        return number == 0;
    }

}


