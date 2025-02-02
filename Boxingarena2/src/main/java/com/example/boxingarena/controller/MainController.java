package com.example.boxingarena.controller;

import com.example.boxingarena.bean.*;
import com.example.boxingarena.controller_app.BoxerRankingControllerApp;
import com.example.boxingarena.controller_app.SubscriptionControllerApp;
import com.example.boxingarena.controller_app.TournamentControllerApp;
import com.example.boxingarena.controller_app.UserControllerApp;
import com.example.boxingarena.exception.DuplicateReceiptException;
import com.example.boxingarena.exception.ReceiptNotFoundException;
import com.example.boxingarena.utilities.FileGenerator;
import com.opencsv.exceptions.CsvValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class MainController implements Initializable {


    private static final String LOCATION = "location";
    private static final String ROLE_1 = "Admin";
    private static final String ROLE_2 = "Boxer";
    private static final String ROLE_3 = "Referee";

    @FXML
    private AnchorPane boxerRanking;

    @FXML
    private TextArea boxerNameLead;

    @FXML
    private TableColumn<BoxerRankingBean, String> boxerNameBoxerRanking;

    @FXML
    private TableColumn<BoxerRankingBean, Integer> boxerNumberMatchBoxerRanking;

    @FXML
    private TableColumn<BoxerRankingBean, Integer> boxerPointBoxerRanking;

    @FXML
    private TableView<BoxerRankingBean> boxerTableLeadbooard;

    @FXML
    private TextField boxerNameConfirm;
    @FXML
    private Spinner<Integer> boxerIdConfirm;
    @FXML
    private TextField boxerTournamentConfirm;
    @FXML
    private Spinner<Integer> boxerTournamentIdConfirm;

    @FXML
    private Spinner<Integer> boxerPointConfirm;

    @FXML
    private Button backToHomeFromRefereeDetail;

    @FXML
    private TableColumn refereeDetailConfirm;

    @FXML
    private TableColumn<SubscriptionBean, String> refereeDetailBoxer;

    @FXML
    private TableColumn<SubscriptionBean, Integer> refereeDetailBoxerId;

    @FXML
    private TextArea refereeDetailName;

    @FXML
    private TableColumn<SubscriptionBean, Integer> refereeDetailPoint;

    @FXML
    private TableColumn<SubscriptionBean, String> refereeDetailTournament;

    @FXML
    private TableColumn<SubscriptionBean, Integer> refereeDetailTournamentId;

    @FXML
    private AnchorPane refereeDetailView;

    @FXML
    private TableView<SubscriptionBean> table;

    @FXML
    public TableColumn refereeDetail;

    @FXML
    public TableColumn<BoxingTournament, Integer> refereeCost;

    @FXML
    public TableColumn<BoxingTournament, LocalDate> refereeDate;

    @FXML
    public TableColumn<BoxingTournament, String> refereeLocation;

    @FXML
    private TextArea refereeName;

    @FXML
    private AnchorPane refereePage;

    @FXML
    public TableView<BoxingTournament> refereeTable;

    @FXML
    public TableColumn<BoxingTournament, Integer> refereeTournamentId;

    @FXML
    public TableColumn<BoxingTournament, String> refereeTournamentName;

    @FXML
    private AnchorPane boxerMySubscription;

    @FXML
    public TableColumn<BoxingTournament, Integer> boxerMySubscriptionCost;

    @FXML
    public TableColumn<BoxingTournament, LocalDate> boxerMySubscriptionDate;

    @FXML
    public TableColumn<BoxingTournament, Integer> boxerMySubscriptionId;

    @FXML
    public TableColumn<BoxingTournament, String> boxerMySubscriptionLocation;

    @FXML
    public TextArea boxerMySubscriptionName;

    @FXML
    public TableView<BoxingTournament> boxerMySubscriptionTable;

    @FXML
    public TableColumn<BoxingTournament, String> boxerMySubscriptionTournamentName;

    @FXML
    private AnchorPane viewAllTournament;

    @FXML
    public TableView<BoxingTournament> tableAllTournament;

    @FXML
    public TableColumn<BoxingTournament, Integer> viewAllTournamentCost;

    @FXML
    public TableColumn<BoxingTournament, LocalDate> viewAllTournamentDate;

    @FXML
    public TableColumn<BoxingTournament, Integer> viewAllTournamentId;

    @FXML
    public TableColumn<BoxingTournament, String> viewAllTournamentLocation;

    @FXML
    public TableColumn<BoxingTournament, String> viewAllTournamentName;

    @FXML
    public TableColumn<BoxingTournament, String> viewAllAction;

    @FXML
    private TextArea boxerName1;

    @FXML
    private AnchorPane boxerPage;

    @FXML
    private TextArea boxerName;

    @FXML
    public TableColumn viewBoxingTournamentButton;

    @FXML
    public TableView<BoxingTournament> tableBoxingTournament;

    @FXML
    public TableColumn<BoxingTournament, Integer> viewBoxingTournamentId;

    @FXML
    public TableColumn<BoxingTournament, String> viewBoxingTournamentName;

    @FXML
    public TableColumn<BoxingTournament, String> viewBoxingTournamentLocation;

    @FXML
    public TableColumn<BoxingTournament, LocalDate> viewBoxingTournamentDate;

    @FXML
    private AnchorPane viewBoxerTournament;

    @FXML
    private TextArea adminNameTournament;

    @FXML
    private AnchorPane adminform;

    @FXML
    private AnchorPane adminCreateTournament;

    @FXML
    private TextArea adminName;

    @FXML
    private AnchorPane adminpage;

    @FXML
    private AnchorPane loginForm;

    @FXML
    private TextArea adminName1;

    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginBtn;

    @FXML
    private ComboBox<String> loginRole;

    @FXML
    private TextField adminUsername;

    @FXML
    private PasswordField adminPassword;

    @FXML
    private Button adminSignupbtn;

    @FXML
    private Hyperlink adminSignin;

    @FXML
    private PasswordField adminConfirmPassword;

    @FXML
    private AnchorPane BoxerForm;

    @FXML
    private TextField boxerUsername;

    @FXML
    private PasswordField boxerPassword;

    @FXML
    private Button boxerSignupbtn;

    @FXML
    private Hyperlink boxerSignin;

    @FXML
    private PasswordField boxerConfirmPassword;

    @FXML
    private AnchorPane refreeForm;

    @FXML
    private TextField refreeUsername;

    @FXML
    private PasswordField refreePassword;

    @FXML
    private Button refreeSignupbtn;

    @FXML
    private Hyperlink refreeSignin;

    @FXML
    private PasswordField refreeConfirmPassword;

    @FXML
    private TextField tournamentName;

    @FXML
    private TextField tournamentLocation;

    @FXML
    private DatePicker tournamentDate;

    @FXML
    private Spinner<Integer> tournamentCost;

    @FXML
    private Spinner<Integer> tournamentNumber;

    private UserBean userGlobal;
    //endregion

    //region methods

    //region login signing

    @FXML
    public void logout(){
        loginForm.setVisible(true);
        adminpage.setVisible(false);
        boxerPage.setVisible(false);
        refereePage.setVisible(false);
    }

    @FXML
    public void login() {
        try {
            UserBean userBean = new UserBean();
            userBean.setUsername(loginUsername.getText());
            userBean.setPassword(loginPassword.getText());
            userBean.checkField(userBean.getUsername(), userBean.getPassword());
            UserControllerApp.login(userBean);
            userGlobal = userBean;

            if(userBean.getRole().equals(ROLE_1)) {
                adminpage.setVisible(true);
                loginForm.setVisible(false);
                adminName.setText(userBean.getUsername());
            }

            if(userBean.getRole().equals(ROLE_2)) {
                boxerPage.setVisible(true);
                loginForm.setVisible(false);
                boxerName.setText(userBean.getUsername());
            }

            if(userBean.getRole().equals(ROLE_3)) {
                loginForm.setVisible(false);
                goToRefereePage();
            }

        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @FXML
    public void switchForm() {
        switch (loginRole.getSelectionModel().getSelectedItem()) {
            case ROLE_1 -> {
                adminform.setVisible(true);
                refreeForm.setVisible(false);
                BoxerForm.setVisible(false);
                loginForm.setVisible(false);
            }
            case ROLE_2 -> {
                adminform.setVisible(false);
                refreeForm.setVisible(false);
                BoxerForm.setVisible(true);
                loginForm.setVisible(false);
            }
            case ROLE_3 -> {
                refreeForm.setVisible(true);
                BoxerForm.setVisible(false);
                refreeForm.setVisible(true);
                loginForm.setVisible(false);
            }
        }
    }

    @FXML
    public void signinForm() {
        loginForm.setVisible(true);
        adminform.setVisible(false);
        refreeForm.setVisible(false);
        BoxerForm.setVisible(false);
    }

    @FXML
    public void signup() {
        try {
            UserBean userBean = new UserBean();
            if (adminform.isVisible()) {
                setVisibleOfSigning(userBean, adminUsername, adminPassword, adminConfirmPassword);
            }
            else if (refreeForm.isVisible()) {
                setVisibleOfSigning(userBean, refreeUsername, refreePassword, refreeConfirmPassword);
            }
            else if (BoxerForm.isVisible()) {
                setVisibleOfSigning(userBean, boxerUsername, boxerPassword, boxerConfirmPassword);
            }

        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    //endregion

    //region Admin

    @FXML
    public void createTournamentForm() {
        adminpage.setVisible(false);
        adminCreateTournament.setVisible(true);
        adminName1.setText(userGlobal.getUsername());

    }

    @FXML
    public void viewBoxingTournamentForm(ActionEvent actionEvent) throws Exception {
        adminpage.setVisible(false);
        adminCreateTournament.setVisible(false);
        viewBoxerTournament.setVisible(true);
        adminNameTournament.setText(userGlobal.getUsername());
        viewBoxingTournamentId.setCellValueFactory(new PropertyValueFactory<BoxingTournament, Integer>("id"));
        viewBoxingTournamentName.setCellValueFactory(new  PropertyValueFactory<BoxingTournament, String>("name"));
        viewBoxingTournamentLocation.setCellValueFactory(new  PropertyValueFactory<BoxingTournament, String>(LOCATION));
        viewBoxingTournamentDate.setCellValueFactory(new  PropertyValueFactory<BoxingTournament, LocalDate>("date"));
        var listMtTournament = TournamentControllerApp.getAllByAdminIdTournaments(userGlobal.getId());
        ObservableList<BoxingTournament> list = FXCollections.observableArrayList(listMtTournament);
        tableBoxingTournament.setItems(list);

        Callback<TableColumn<BoxingTournament, String >, TableCell<BoxingTournament, String>> cellFactory =(param) ->{
            return new TableCell<BoxingTournament, String>(){
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else {
                        final Button payAndSubButton = new Button("Download");
                        payAndSubButton.setOnAction(event -> {
                            BoxingTournament boxingTournament = getTableView().getItems().get(getIndex());
                            try {
                                download(boxingTournament);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
                        setGraphic(payAndSubButton);
                    }
                }

            };
        };

        viewBoxingTournamentButton.setCellFactory(cellFactory);
    }

    @FXML
    public void backToHomeFromCreate() {
        adminCreateTournament.setVisible(false);
        adminpage.setVisible(true);
    }

    @FXML
    public void backToHomeFromBoxingTournament(){
        viewBoxerTournament.setVisible(false);
        adminpage.setVisible(true);
    }
    @FXML
    public void createTournament() throws Exception {

        TournamentBean tournamentBean = new TournamentBean();
        tournamentBean.setName(tournamentName.getText());
        tournamentBean.setLocation(tournamentLocation.getText());
        tournamentBean.setCost(tournamentCost.getValue());
        tournamentBean.setNumber(tournamentNumber.getValue());
        tournamentBean.setDate(tournamentDate.getValue());
        tournamentBean.checkField(tournamentBean.getName(), tournamentBean.getLocation(), tournamentBean.getDate(), tournamentBean.getCost(), tournamentBean.getNumber());

        TournamentControllerApp.createTournament(userGlobal, tournamentBean);

    }

    //endregion

    //region Boxer

    @FXML
    public void joinTournament() throws Exception {
        boxerPage.setVisible(false);
        viewAllTournament.setVisible(true);
        boxerName1.setText(userGlobal.getUsername());
        viewAllTournamentId.setCellValueFactory(new PropertyValueFactory<BoxingTournament, Integer>("id"));
        viewAllTournamentName.setCellValueFactory(new PropertyValueFactory<BoxingTournament, String>("name"));
        viewAllTournamentLocation.setCellValueFactory(new  PropertyValueFactory<BoxingTournament, String>(LOCATION));
        viewAllTournamentDate.setCellValueFactory(new PropertyValueFactory<BoxingTournament, LocalDate>("date"));
        viewAllTournamentCost.setCellValueFactory(new PropertyValueFactory<BoxingTournament, Integer>("cost"));
        var listMtTournament = TournamentControllerApp.getAllTournaments();
        ObservableList<BoxingTournament> list = FXCollections.observableArrayList(listMtTournament);
        tableAllTournament.setItems(list);

        Callback<TableColumn<BoxingTournament, String >, TableCell<BoxingTournament, String>> cellFactory =(param) ->{
            return new TableCell<BoxingTournament, String>(){
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else {
                        final Button payAndSubButton = new Button("PayAndSub");
                        payAndSubButton.setOnAction(event -> {
                            BoxingTournament boxingTournament = getTableView().getItems().get(getIndex());
                            try {
                                payAndSubscription(boxingTournament);
                                ReceiptBean receiptBean = new ReceiptBean();
                                List<SubscriptionBean> subscriptionBeanList = SubscriptionControllerApp.getTournamentSubscription(boxingTournament.getId());
                                for(SubscriptionBean subscription : subscriptionBeanList) {
                                    receiptBean.setDate(LocalDate.now());
                                    receiptBean.setIdBoxer(subscription.getIdBoxer());
                                    receiptBean.setIdTournament(subscription.getIdTournament());
                                    SubscriptionControllerApp.createReceipt(receiptBean);
                                }
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
                        setGraphic(payAndSubButton);
                    }
                }

            };
        };

        viewAllAction.setCellFactory(cellFactory);

    }

    @FXML
    public void viewBoxerRanking(ActionEvent actionEvent) throws SQLException {
        boxerPage.setVisible(false);
        boxerNameLead.setText(userGlobal.getUsername());
        boxerRanking.setVisible(true);
        boxerNameBoxerRanking.setCellValueFactory(new PropertyValueFactory<BoxerRankingBean, String>("boxer"));
        boxerNumberMatchBoxerRanking.setCellValueFactory(new PropertyValueFactory<BoxerRankingBean, Integer>("numberMatches"));
        boxerPointBoxerRanking.setCellValueFactory(new  PropertyValueFactory<BoxerRankingBean, Integer>("totalPoints"));
        var boxerRanking1 = BoxerRankingControllerApp.getBoxerRanking();
        ObservableList<BoxerRankingBean> list = FXCollections.observableArrayList(boxerRanking1);
        boxerTableLeadbooard.setItems(list);
    }

    @FXML
    public void viewMySubscription() throws SQLException {
        boxerPage.setVisible(false);
        boxerMySubscription.setVisible(true);
        boxerMySubscriptionName.setText(userGlobal.getUsername());
        boxerMySubscriptionId.setCellValueFactory(new PropertyValueFactory<BoxingTournament, Integer>("id"));
        boxerMySubscriptionTournamentName.setCellValueFactory(new PropertyValueFactory<BoxingTournament, String>("name"));
        boxerMySubscriptionLocation.setCellValueFactory(new  PropertyValueFactory<BoxingTournament, String>(LOCATION));
        boxerMySubscriptionDate.setCellValueFactory(new PropertyValueFactory<BoxingTournament, LocalDate>("date"));
        boxerMySubscriptionCost.setCellValueFactory(new PropertyValueFactory<BoxingTournament, Integer>("cost"));
        var listMtTournament = SubscriptionControllerApp.getAllSubscriptionsByUser(userGlobal.getId());
        ObservableList<BoxingTournament> list = FXCollections.observableArrayList(listMtTournament);
        boxerMySubscriptionTable.setItems(list);

    }

    @FXML
    public void backToHomeFromBoxerRanking(){
        boxerRanking.setVisible(false);
        boxerPage.setVisible(true);
    }

    @FXML
    public void backToHomeFromAllTournament() {
        viewAllTournament.setVisible(false);
        boxerPage.setVisible(true);
    }

    @FXML
    public void backToHomeFromSubscriptionTable(){
        boxerMySubscription.setVisible(false);
        boxerPage.setVisible(true);
    }

    //endregion

    //region Referee

    private void goToRefereePage() throws Exception {
        refereePage.setVisible(true);
        refereeName.setText(userGlobal.getUsername());
        refereeTournamentId.setCellValueFactory(new PropertyValueFactory<BoxingTournament, Integer>("id"));
        refereeTournamentName.setCellValueFactory(new  PropertyValueFactory<BoxingTournament, String>("name"));
        refereeLocation.setCellValueFactory(new  PropertyValueFactory<BoxingTournament, String>(LOCATION));
        refereeDate.setCellValueFactory(new  PropertyValueFactory<BoxingTournament, LocalDate>("date"));
        refereeCost.setCellValueFactory(new  PropertyValueFactory<BoxingTournament, Integer>("cost"));
        var listMtTournament = TournamentControllerApp.getAllTournaments();
        ObservableList<BoxingTournament> list = FXCollections.observableArrayList(listMtTournament);
        refereeTable.setItems(list);

        Callback<TableColumn<BoxingTournament, String >, TableCell<BoxingTournament, String>> cellFactory =(param) ->{
            return new TableCell<BoxingTournament, String>(){
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else {
                        final Button payAndSubButton = new Button("Detail");
                        payAndSubButton.setOnAction(event -> {
                            BoxingTournament boxingTournament = getTableView().getItems().get(getIndex());
                            try {
                                detail(boxingTournament.getId());
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
                        setGraphic(payAndSubButton);
                    }
                }

            };
        };

        refereeDetail.setCellFactory(cellFactory);
    }

    private void detail(int id) throws SQLException {
        refereePage.setVisible(false);
        refereeDetailView.setVisible(true);
        refereeDetailName.setText(userGlobal.getUsername());
        refereeDetailBoxerId.setCellValueFactory(new PropertyValueFactory<SubscriptionBean, Integer>("idBoxer"));
        refereeDetailBoxer.setCellValueFactory(new  PropertyValueFactory<SubscriptionBean, String>("boxer"));
        refereeDetailTournament.setCellValueFactory(new  PropertyValueFactory<SubscriptionBean, String>("tournament"));
        refereeDetailTournamentId.setCellValueFactory(new  PropertyValueFactory<SubscriptionBean, Integer>("idTournament"));
        refereeDetailPoint.setCellValueFactory(new  PropertyValueFactory<SubscriptionBean, Integer>("points"));
        var listMtTournament = SubscriptionControllerApp.getTournamentSubscription(id);
        ObservableList<SubscriptionBean> list = FXCollections.observableArrayList(listMtTournament);
        table.setItems(list);

        Callback<TableColumn<SubscriptionBean, String >, TableCell<SubscriptionBean, String>> cellFactory =(param) ->{
            return new TableCell<SubscriptionBean, String>(){
                @Override
                public void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else {
                        final Button confirmVote = new Button("ConfirmVote");
                        confirmVote.setOnAction(event -> {
                            SubscriptionBean subscription = getTableView().getItems().get(getIndex());
                            try {
                                confirmVote(subscription);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        });
                        setGraphic(confirmVote);
                    }
                }

            };
        };

        refereeDetailConfirm.setCellFactory(cellFactory);
    }
    @FXML
    public void backToHomeFromRefereeDetail(){
        refereeDetailView.setVisible(false);
        refereePage.setVisible(true);
    }

    @FXML
    public void confirm() throws SQLException {
        var boxer = boxerNameConfirm.getText();
        var boxerId = boxerIdConfirm.getValue();
        var tournament = boxerTournamentConfirm.getText();
        var tournamentId = boxerTournamentIdConfirm.getValue();
        var point = boxerPointConfirm.getValue();

        new SubscriptionBean(boxerId, tournamentId, point, boxer, tournament);
        SubscriptionControllerApp.updateSubscription(point, boxerId, tournamentId);

        boxerNameConfirm.setText("");
        boxerIdConfirm.getValueFactory().setValue(0);
        boxerTournamentIdConfirm.getValueFactory().setValue(0);
        boxerPointConfirm.getValueFactory().setValue(0);
        boxerTournamentConfirm.setText("");

        detail(tournamentId);
    }
    //endregion

    //endregion

    //region private methods

    public void confirmVote(SubscriptionBean subscription) throws SQLException {
        boxerNameConfirm.setText(subscription.getBoxer());
        boxerTournamentConfirm.setText(subscription.getTournament());
        boxerIdConfirm.getValueFactory().setValue(subscription.getIdBoxer());
        boxerIdConfirm.setEditable(false);
        boxerTournamentIdConfirm.getValueFactory().setValue(subscription.getIdTournament());
        boxerIdConfirm.setEditable(false);
        boxerPointConfirm.getValueFactory().setValue(subscription.getPoints());
    }

    public void payAndSubscription(BoxingTournament boxingTournament) throws Exception {
        TournamentBean tournamentBean = new TournamentBean(boxingTournament.getId(), boxingTournament.getName());
        SubscriptionControllerApp.payAndSubscription(userGlobal, tournamentBean);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Pay and Subscription done");
        alert.show();
    }

    public void download(BoxingTournament tournament) throws SQLException, CsvValidationException, ReceiptNotFoundException, IOException, DuplicateReceiptException {
        var boxerList = SubscriptionControllerApp.getTournamentSubscription(tournament.getId());
        FileGenerator.generateFile(tournament.getName(), boxerList.toString());
        Logger logger = Logger.getLogger(getClass().getName());
        logger.info("File created successfully!");
    }

    private void setRoleList() {
        String[] role = {ROLE_1, ROLE_2, ROLE_3};
        List<String> listRole = new ArrayList<>(Arrays.asList(role));

        ObservableList<String> listData = FXCollections.observableArrayList(listRole);
        loginRole.setItems(listData);
    }

    private void setVisibleOfSigning(UserBean userBean, TextField adminUsername, PasswordField adminPassword, PasswordField adminConfirmPassword) throws Exception {
        userBean.setUsername(adminUsername.getText());
        userBean.setPassword(adminPassword.getText());
        userBean.setCheckPassword(adminConfirmPassword.getText());
        userBean.setRole(loginRole.getValue());
        UserBean.checkIfPassIsEqual(userBean.getPassword(), userBean.getCheckPassword());
        boolean check = UserControllerApp.signing(userBean);
        if(check) {
            adminform.setVisible(false);
            loginForm.setVisible(true);
        }
    }

    //endregion
    
    //region override

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setRoleList();
        SpinnerValueFactory<Integer> spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,1000,1);
        tournamentCost.setValueFactory(spinner);

        SpinnerValueFactory<Integer> spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,1);
        tournamentNumber.setValueFactory(spinner2);

        SpinnerValueFactory<Integer> spinner3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,10000, 0);
        boxerPointConfirm.setValueFactory(spinner3);

        SpinnerValueFactory<Integer> spinner4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000000000, 0);
        boxerTournamentIdConfirm.setValueFactory(spinner4);

        SpinnerValueFactory<Integer> spinner5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1000000000, 0);
        boxerIdConfirm.setValueFactory(spinner5);


    }

    //endregion
}