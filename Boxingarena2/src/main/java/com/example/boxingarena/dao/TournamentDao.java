package com.example.boxingarena.dao;

import com.example.boxingarena.database.DbConnection;
import com.example.boxingarena.database.Queries;
import com.example.boxingarena.entity.Tournament;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TournamentDao {

    private final Logger logger = Logger.getLogger(TournamentDao.class.getName());

    public void createTournament(Tournament tournament, int userId) throws SQLException {
        Connection conn = DbConnection.getConnection();
        try (
                PreparedStatement stmt = conn.prepareStatement(
                        Queries.CREATE_TOURNAMENT,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                )
        ) {
            stmt.setString(1, tournament.getName());
            stmt.setString(2, tournament.getLocation());
            stmt.setInt(3, tournament.getNumber());
            stmt.setInt(4, tournament.getCost());
            stmt.setDate(5, Date.valueOf(tournament.getDate()));
            stmt.setString(6, tournament.getCreatorName());
            stmt.setInt(7, userId);
            int result = stmt.executeUpdate();

            if (result == 1) {
                logger.info("Tournament created");
            } else {
                throw new SQLException("Tournament not created");
            }
        }
    }

    public List<Tournament> getAllByAdminIdTournaments(int id) throws SQLException {
        Connection conn = DbConnection.getConnection();
        try (
                PreparedStatement stmt = conn.prepareStatement(
                        Queries.FIND_ALL_TOURNAMENTS_BY_ADMIN_ID,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                )

        ){
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return getTournaments(rs);
            } catch (Exception e) {
                throw new SQLException(e);
            }
        }
    }

    public List<Tournament> getAllTournaments() throws SQLException {
        Connection conn = DbConnection.getConnection();
        try (
                PreparedStatement stmt = conn.prepareStatement(
                        Queries.FIND_ALL_TOURNAMENTS,
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                )
        ){
            try (ResultSet rs = stmt.executeQuery()) {
                return getTournaments(rs);
            } catch (Exception e) {
                throw new SQLException(e);
            }
        }
    }

    private List <Tournament> getTournaments (ResultSet rs) throws SQLException {
        if (!rs.first()) {
            throw new SQLException("Tournaments not found");
        }
        List<Tournament> tournaments = new ArrayList<>();
        do {
            tournaments.add(new Tournament(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("location"),
                    rs.getInt("number"),
                    rs.getInt("cost"),
                    rs.getDate("date").toLocalDate(),
                    rs.getString("creatorName")
            ));
        }
        while (rs.next());
        return tournaments;
    }

}
