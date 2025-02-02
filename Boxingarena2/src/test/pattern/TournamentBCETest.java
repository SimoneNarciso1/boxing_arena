package test.pattern;

import com.example.BoxengArena.bean.TournamentBean;
import com.example.BoxengArena.bean.UserBean;
import com.example.boxingarena.controller_app.TournamentControllerApp;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

    class TournamentBCETest {
    @Test
    void TournamnetBCETest(){
        assertDoesNotThrow(() -> {
            UserBean userBean = new UserBean(5, "testUsername", "Admin");
            TournamentBean tournamentBean = new TournamentBean(0, "torneoTest", "locationTest", 20, 25, LocalDate.now());
            TournamentControllerApp.createTournament(userBean, tournamentBean);
        },"Creating TournamentControllerApp should not throw any exception");
    }
}
