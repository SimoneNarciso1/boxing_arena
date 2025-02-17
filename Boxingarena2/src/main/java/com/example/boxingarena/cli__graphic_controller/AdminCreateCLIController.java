package com.example.boxingarena.cli__graphic_controller;

import com.example.boxingarena.bean.TournamentBean;
import com.example.boxingarena.bean.UserBean;
import com.example.boxingarena.controller_app.TournamentControllerApp;
import com.example.boxingarena.exception.InvalidFormatException;
import com.example.boxingarena.utilities.CLIPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class AdminCreateCLIController {

    public void createTournament(int id) throws IOException, SQLException, InvalidFormatException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        UserBean globalBean = new UserBean();
        globalBean.setId(id);
        LocalDate date = null;

        CLIPrinter.printMessage("Tournament Name: ");
        String tournamentName = reader.readLine().trim();

        CLIPrinter.printMessage("Location: ");
        String location = reader.readLine().trim();

        CLIPrinter.printMessage("Cost: ");
        int cost = Integer.parseInt(reader.readLine().trim());

        CLIPrinter.printMessage("Participant Number: ");
        int participant = Integer.parseInt(reader.readLine().trim());

        CLIPrinter.printMessage("Tournament Date in format yy/MM/dd: ");
        String stringDate = reader.readLine().trim();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yy/MM/dd");
        date = LocalDate.parse(stringDate, dateFormat);

        TournamentBean tournamentBean = new TournamentBean();
        tournamentBean.setName(tournamentName);
        tournamentBean.setLocation(location);
        tournamentBean.setCost(cost);
        tournamentBean.setNumber(participant);
        tournamentBean.setDate(date);


        tournamentBean.checkField(tournamentBean.getName(), tournamentBean.getLocation(), tournamentBean.getDate(), tournamentBean.getCost(), tournamentBean.getNumber());
        TournamentControllerApp.createTournament(globalBean, tournamentBean);
          new AdminCLIController().start(id);
    }
}
