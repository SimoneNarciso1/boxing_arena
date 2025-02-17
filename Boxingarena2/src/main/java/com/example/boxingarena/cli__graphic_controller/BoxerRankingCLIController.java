package com.example.boxingarena.cli__graphic_controller;


import com.example.boxingarena.controller_app.BoxerRankingControllerApp;
import com.example.boxingarena.bean.BoxerRankingBean;

import java.sql.SQLException;
import java.util.List;



public class BoxerRankingCLIController {

    public void viewRanking(int id) throws SQLException {
        List<BoxerRankingBean> boxerRankings = BoxerRankingControllerApp.getBoxerRanking();

        if (boxerRankings.isEmpty()) {
            System.out.println("Nessun dato di classifica disponibile.");
        } else {
            System.out.println("Classifica Pugili:");
            System.out.printf("%-30s %-15s %-15s\n", "Nome Pugile", "Numero Match", "Punti Totali");
            for (BoxerRankingBean ranking : boxerRankings) {
                System.out.printf("%-30s %-15d %-15d\n",
                        ranking.getBoxer(),
                        ranking.getNumberMatches(),
                        ranking.getTotalPoints());
            }
        }
        new BoxerCLIController().start(id);
    }
}
