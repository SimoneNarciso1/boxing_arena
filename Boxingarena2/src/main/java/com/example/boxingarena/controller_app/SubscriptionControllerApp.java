package com.example.boxingarena.controller_app;

import com.example.boxingarena.bean.BoxingTournament;
import com.example.boxingarena.bean.SubscriptionBean;
import com.example.boxingarena.bean.TournamentBean;
import com.example.boxingarena.bean.UserBean;
import com.example.boxingarena.bean.ReceiptBean;
import com.example.boxingarena.dao.ReceiptDAO;
import com.example.boxingarena.dao.ReceiptDAOFactorySingleton;
import com.example.boxingarena.dao.SubscriptionDao;
import com.example.boxingarena.entity.Receipt;
import com.example.boxingarena.entity.Subscription;
import com.example.boxingarena.entity.Tournament;
import com.example.boxingarena.entity.User;
import com.example.boxingarena.exception.DuplicateReceiptException;
import com.example.boxingarena.exception.ReceiptNotFoundException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SubscriptionControllerApp {

    private SubscriptionControllerApp() {
        throw new IllegalStateException("Utility class");
    }

    public static void payAndSubscription(UserBean userBean, TournamentBean tournamentBean) throws SQLException {
        SubscriptionDao subscriptionDao = new SubscriptionDao();
        User user = new User(userBean.getId(), userBean.getUsername());
        Tournament tournament = new Tournament(tournamentBean.getId(), tournamentBean.getName());
        subscriptionDao.payAndSubscription(user, tournament);
        Logger logger = Logger.getLogger(SubscriptionControllerApp.class.getName());
        logger.info("You have subscription and pay");
    }

    public static List<SubscriptionBean> getTournamentSubscription(int id) throws SQLException {
        SubscriptionDao subscriptionDao = new SubscriptionDao();
        List<SubscriptionBean> subscriptionBeans = new ArrayList<>();
        var subscriptionList = subscriptionDao.getTournamentSubscription(id);
        for (Subscription subscription : subscriptionList) {
            var boxer = subscription.getBoxerEntity();
            var tournament = subscription.getTournamentEntity();
            var boxerId = subscription.getIdBoxerEntity();
            var tournamentId = subscription.getIdTournamentEntity();
            var vote = subscription.getPointEntity();
            SubscriptionBean subscriptionBean = new SubscriptionBean(boxerId, tournamentId, vote, boxer,tournament);
            subscriptionBeans.add(subscriptionBean);
        }
        return subscriptionBeans;
    }

    public static List<BoxingTournament> getAllSubscriptionsByUser(int id) throws SQLException {
        SubscriptionDao subscriptionDao = new SubscriptionDao();
        var tournamentList = subscriptionDao.getAllSubscriptionsByUser(id);
        List<BoxingTournament> tournamentBeans = new ArrayList<>();
        for (Tournament tournament : tournamentList) {
            var id1 = tournament.getId();
            var name = tournament.getName();
            var location = tournament.getLocation();
            var cost = tournament.getCost();
            var date = tournament.getDate();
            BoxingTournament tournamentBean = new BoxingTournament(id1, name, location, date, cost);
            tournamentBeans.add(tournamentBean);
        }
        return tournamentBeans;
    }

    public static void updateSubscription(int point, int id, int tournamentId) throws SQLException {
        SubscriptionDao subscriptionDao = new SubscriptionDao();
        subscriptionDao.updateSubscription(point, id, tournamentId);
    }

    public static void createReceipt(ReceiptBean receiptBean) throws IOException, SQLException, CsvValidationException, ReceiptNotFoundException, DuplicateReceiptException {

        Receipt receipt = new Receipt(LocalDate.now(), receiptBean.getIdBoxer(), receiptBean.getIdTournament());

        ReceiptDAOFactorySingleton receiptDAOFactory = ReceiptDAOFactorySingleton.getInstance();
        ReceiptDAO receiptDAO = receiptDAOFactory.createReceiptDAO();
        receiptDAO.createReceipt(receipt);
    }
}
