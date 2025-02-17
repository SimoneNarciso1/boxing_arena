package com.example.boxingarena.cli__graphic_controller;

import com.example.boxingarena.bean.BoxingTournament;
import com.example.boxingarena.controller_app.TournamentControllerApp;
import com.example.boxingarena.exception.InvalidFormatException;
import com.example.boxingarena.utilities.CLIPrinter;

import java.sql.SQLException;
import java.util.List;

public class AdminViewCLIController {

    public void viewTournament(int idUser) throws SQLException, InvalidFormatException {
        List<BoxingTournament> tournaments = TournamentControllerApp.getAllByAdminIdTournaments(idUser);

        if (tournaments.isEmpty()) {
            CLIPrinter.printMessage("Nessun torneo disponibile.");
        } else {
            CLIPrinter.printMessage("Elenco tornei disponibili:");
            String header = String.format("%-10s %-30s %-20s %-15s", "ID", "Nome", "Località", "Data");
            CLIPrinter.printMessage(header);
            for (BoxingTournament tournament : tournaments) {
                String tournamentDetails = String.format("%-10d %-30s %-20s %-15s",
                        tournament.getId(),
                        tournament.getName(),
                        tournament.getLocation(),
                        tournament.getDate().toString());
                CLIPrinter.printMessage(tournamentDetails);
            }
        }
        new AdminCLIController().start(idUser);
    }
}
