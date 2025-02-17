package com.example.boxingarena.cli__graphic_controller;


import com.example.boxingarena.controller_app.BoxerRankingControllerApp;
import com.example.boxingarena.bean.BoxerRankingBean;
import com.example.boxingarena.exception.InvalidFormatException;
import com.example.boxingarena.utilities.CLIPrinter;

import java.sql.SQLException;
import java.util.List;



public class BoxerRankingCLIController {

    public void viewRanking(int id) throws SQLException, InvalidFormatException {
        List<BoxerRankingBean> boxerRankings = BoxerRankingControllerApp.getBoxerRanking();

        if (boxerRankings.isEmpty()) {
           CLIPrinter.printMessage("\nNessun dato di classifica disponibile.");
        } else {
            CLIPrinter.printMessage("\nClassifica Pugili:");
           String header = String.format("%-30s %-15s %-15s%n", "Nome Pugile", "Numero Match", "Punti Totali");
           CLIPrinter.printMessage(header);
            for (BoxerRankingBean ranking : boxerRankings) {
                String rankings = String.format("%-30s %-15d %-15d%n",
                        ranking.getBoxer(),
                        ranking.getNumberMatches(),
                        ranking.getTotalPoints());
                CLIPrinter.printMessage(rankings);
            }
        }
        new BoxerCLIController().start(id);
    }
}
