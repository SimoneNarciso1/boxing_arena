package com.example.boxingarena.controller_app;

import com.example.boxingarena.bean.TournamentBean;
import com.example.boxingarena.bean.UserBean;
import com.example.boxingarena.bean.BoxingTournament;
import com.example.boxingarena.dao.TournamentDao;
import com.example.boxingarena.entity.Tournament;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TournamentControllerApp {

    private TournamentControllerApp() {
        throw new IllegalStateException("Utility class");
    }

    public static void createTournament(UserBean user, TournamentBean tournamentBean) throws SQLException {
        TournamentDao tournamentDao = new TournamentDao();
        Tournament entity = new Tournament(tournamentBean.getName(), tournamentBean.getLocation(), tournamentBean.getNumber(), tournamentBean.getCost(), tournamentBean.getDate(), user.getUsername());
        tournamentDao.createTournament(entity, user.getId());
    }

    public static List<BoxingTournament> getAllByAdminIdTournaments(Integer id) throws SQLException {
        TournamentDao tournamentDao = new TournamentDao();
        List<Tournament> tournamentList = tournamentDao.getAllByAdminIdTournaments(id);
        List<BoxingTournament> tournamentBeans = new ArrayList<>();
        for (Tournament tournament : tournamentList) {
            var tournamentId = tournament.getId();
            var name = tournament.getName();
            var location = tournament.getLocation();
            var date = tournament.getDate();
            var cost = tournament.getCost();
            BoxingTournament tournamentBean = new BoxingTournament(tournamentId, name, location, date,cost);
            tournamentBeans.add(tournamentBean);
        }
        return tournamentBeans;
    }

    public static List<BoxingTournament> getAllTournaments() throws ExceptionInInitializerError, SQLException {
        TournamentDao tournamentDao = new TournamentDao();
        List<Tournament> tournamentList = tournamentDao.getAllTournaments();
        List<BoxingTournament> tournamentBeans = new ArrayList<>();
        for (Tournament tournament : tournamentList) {
            var id = tournament.getId();
            var name = tournament.getName();
            var location = tournament.getLocation();
            var cost = tournament.getCost();
            var date = tournament.getDate();
            BoxingTournament tournamentBean = new BoxingTournament(id, name, location, date, cost);
            tournamentBeans.add(tournamentBean);
        }
        return tournamentBeans;
    }
}
