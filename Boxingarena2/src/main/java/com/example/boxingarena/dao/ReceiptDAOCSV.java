package com.example.boxingarena.dao;


import com.example.boxingarena.entity.Receipt;
import com.example.boxingarena.exception.ReceiptNotFoundException;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceiptDAOCSV extends ReceiptDAO {
    private static final Integer INDEX_SUBSCRIPTION_DATE = 0;
    private static final Integer INDEX_BOXER_ID = 1;
    private static final Integer INDEX_TOURNAMENT_ID = 2;
    private final Logger logger = Logger.getLogger(ReceiptDAOCSV.class.getName());
    private static final String CSV_FILE_NAME = "src/main/resources/com/example/kartgp/receipts/ReceiptDBlocal.csv";
    private final File fd;

    public ReceiptDAOCSV() throws IOException {
        this.fd = new File(CSV_FILE_NAME);
        if (!fd.exists()) {
            boolean fileCreated = fd.createNewFile();
            if (fileCreated) {
                logger.log(Level.INFO, "File created: " + CSV_FILE_NAME);
            } else {
                logger.log(Level.INFO, "File already exists: " + CSV_FILE_NAME);
            }
        }
    }


    @Override
    public void createReceipt(Receipt receipt) throws IOException {

        insertReceipt(this.fd, receipt);
    }

    private static synchronized void insertReceipt(File fd, Receipt receipt) throws IOException {

        try (CSVWriter csvWriter = new CSVWriter(new BufferedWriter(new FileWriter(fd, true)))) { //Security Hotspot
            String[] receiptRecord = new String[3];
            receiptRecord[INDEX_SUBSCRIPTION_DATE] = String.valueOf(receipt.getDate());
            receiptRecord[INDEX_BOXER_ID] = String.valueOf(receipt.getIdBoxerEntity());
            receiptRecord[INDEX_TOURNAMENT_ID] = String.valueOf(receipt.getIdTournamentEntity());
            csvWriter.writeNext(receiptRecord);
            csvWriter.flush();
        }
    }



    @Override
    public Receipt retrieveReceipt(int userId) throws SQLException, ReceiptNotFoundException, CsvValidationException, IOException {
        return retrieveByUserId(this.fd, userId);
    }

    private synchronized Receipt retrieveByUserId(File fd, int userId) throws IOException, CsvValidationException, ReceiptNotFoundException {
        try (CSVReader csvReader = new CSVReader(new BufferedReader(new FileReader(fd)))) {
            String[] recordToTake;

            Receipt receipt = new Receipt();

            while ((recordToTake = csvReader.readNext()) != null) {
                LocalDate subscriptionDate = LocalDate.parse(String.valueOf(recordToTake[INDEX_SUBSCRIPTION_DATE]));
                int boxerId = Integer.parseInt(recordToTake[INDEX_BOXER_ID]);
                int tournamentId = Integer.parseInt(recordToTake[INDEX_TOURNAMENT_ID]);
                receipt = new Receipt(subscriptionDate, boxerId, tournamentId);

            }

            if (receipt.getIdBoxerEntity() != userId) {
                throw new ReceiptNotFoundException("No Receipt Found matching with id: " + userId);
            }

            return receipt;
        }
    }

}