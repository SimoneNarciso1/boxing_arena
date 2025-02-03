/*package com.example.BoxingArena.controller_app;

import com.example.BoxingArena.bean.BoxerRankingBean;
import com.example.BoxingArena.dao.BoxerRankingDao;
import com.example.BoxingArena.entity.BoxerRanking;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoxerRankingControllerApp {
    private BoxerRankingControllerApp() {
        throw new IllegalStateException("Utility class");
    }
    public static List<BoxerRankingBean> getBoxerRanking() throws SQLException {
        BoxerRankingDao boxRankingDao = new BoxerRankingDao();
        var boxerRankingBean = new ArrayList<BoxerRankingBean>();
        var boxerRankings = boxRankingDao.getBoxerRanking();
        for (BoxerRanking boxerRanking : boxerRankings) {
            boxerRankingBean.add(new BoxerRankingBean(boxerRanking.getBoxerName(), boxerRanking.getMatches(), boxerRanking.getPoints()));
        }
        return  boxerRankingBean;
    }
}
*/

package com.example.boxingarena.controller_app;

import com.example.boxingarena.bean.BoxerRankingBean;
import com.example.boxingarena.dao.BoxerRankingDao;
import com.example.boxingarena.entity.BoxerRanking;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoxerRankingControllerApp {
    private BoxerRankingControllerApp() {
        throw new IllegalStateException("Utility class");
    }
    public static List<BoxerRankingBean> getBoxerRanking() throws SQLException {
        var leadBoardsBean = new ArrayList<BoxerRankingBean>();
        var leadBoards = BoxerRankingDao.getBoxerRanking();
        for (BoxerRanking leadBoard : leadBoards) {
            leadBoardsBean.add(new BoxerRankingBean(leadBoard.getBoxerName(), leadBoard.getMatches(), leadBoard.getPoints()));
        }
        return  leadBoardsBean;
    }
}