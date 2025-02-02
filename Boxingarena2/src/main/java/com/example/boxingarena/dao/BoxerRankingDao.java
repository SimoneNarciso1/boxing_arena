package com.example.boxingarena.dao;

import com.example.boxingarena.database.DbConnection;
import com.example.boxingarena.entity.BoxerRanking;
import com.example.boxingarena.database.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoxerRankingDao {

    public static List<BoxerRanking> getBoxerRanking() throws SQLException {
        Connection conn = DbConnection.getConnection();
        try (
                PreparedStatement stmt = conn.prepareStatement(
                        Queries.GET_BOXER_RANKINGS, // Questa query dovrà essere definita in 'Queries'
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY
                )
        ){
            ResultSet rs = stmt.executeQuery();
            if (!rs.first()) {
                throw new SQLException("No rankings found.");
            }
            List<BoxerRanking> rankings = new ArrayList<>();
            do {
                rankings.add(new BoxerRanking(
                        rs.getString("boxer"),
                        rs.getInt("NumberMatch"),
                        rs.getInt("totalPoints")  // Assumendo che ci siano queste colonne
                ));
            } while (rs.next());
            return rankings;

        } catch (SQLException e) {
            throw new IllegalArgumentException("Database error: " + e.getMessage(), e);
        }
    }
}
