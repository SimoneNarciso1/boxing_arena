package com.example.boxingarena.database;

public class Queries {

    private Queries() {
        throw new IllegalStateException("Utility class");
    }

    public static final String FIND_USER =
            "SELECT * FROM user WHERE username = ? AND password = ?";

    public static final String CREATE_USER =
            "INSERT INTO user (username, password, role) VALUES (?, ?, ?)";

    public static final String CREATE_TOURNAMENT =
            "INSERT INTO tournament (name, location, number, cost, date, creatorName, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String FIND_ALL_TOURNAMENTS_BY_ADMIN_ID =
            "SELECT * FROM tournament WHERE user_id = ?";

    public static final String FIND_ALL_TOURNAMENTS =
            "SELECT * FROM tournament";

    public static final String DO_SUBSCRIPTION =
            "INSERT INTO subscription (user_id, tournament_id, points, boxer , tournament) VALUES (?, ?, ?, ?, ?)";

    public static final String GET_TOURNAMENT_BY_SUBSCRIPTION =
            "SELECT * FROM subscription WHERE tournament_id = ?";

    public static final String GET_ALL_MY_SUBSCRIPTIONS =
            "SELECT * FROM tournament INNER JOIN subscription ON tournament.id = subscription.tournament_id where subscription.user_id = ?";

    public static final String UPDATE_VOTE =
            "UPDATE subscription SET points = ? WHERE user_id = ? AND tournament_id = ?";

    public static final String GET_BOXER_RANKINGS =
            "CALL boxerRanking()";

    public static final String INSERT_RECEIPT =
            "INSERT INTO receipt (date, subscription_user_id, subscription_tournament_id) VALUES (?,?,?)";
}
