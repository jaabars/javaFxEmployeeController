package kg.megacom.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import kg.megacom.http.HttpHelper;
import kg.megacom.models.Employee;

public class MainEmployeeViewFormController {
    public static int num=1;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem mnClose;

    @FXML
    private MenuItem mnAddEmployee;

    @FXML
    private MenuItem mnAddPosition;

    @FXML
    private MenuItem mnEdit;

    @FXML
    private MenuItem mnDelete;

    @FXML
    private MenuItem mnAbout;

    @FXML
    private TableView<Employee> tblViewEmployee;

    @FXML
    private TableColumn<Employee,Long> colmNum;

    @FXML
    private TableColumn<Employee, String> colmName;

    @FXML
    private TableColumn<Employee, String> colmPosition;

    @FXML
    private TableColumn<Employee, Boolean> colmActive;

    @FXML
    private TableColumn<Employee, Long> colmId;

    @FXML
    void onMouseClicked(MouseEvent event) {



    }



    @FXML
    void onMenuItemClicked(ActionEvent event) {
       if (event.getSource().equals(mnAddEmployee)){
           try {
                Parent root=new FXMLLoader().load(getClass().getResource("/layouts/employeeAddForm.fxml"));
                Stage stage=new Stage();
               stage.setScene(new Scene(root));
               stage.show();
               stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                   @Override
                   public void handle(WindowEvent event) {
                       try {
                           refresh();
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
               });



           } catch (IOException e) {
              e.printStackTrace();
          }

       }else if(event.getSource().equals(mnAddPosition)){
           Parent root= null;
            try {
              root = new FXMLLoader().load(getClass().getResource("/layouts/positionAddForm.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
           Stage stage= new Stage();
            stage.setScene(new Scene(root));
           stage.show();

       }else if(event.getSource().equals(mnEdit)){
           editCurrentUser();

       }else if(event.getSource().equals(mnAbout)){
           Parent root= null;
           try {
               root = new FXMLLoader().load(getClass().getResource("/layouts/aboutForm.fxml"));
           } catch (IOException e) {
               e.printStackTrace();
           }
           Stage stage=new Stage();
           stage.setScene(new Scene(root));
           stage.show();


       }else if(event.getSource().equals(mnClose)){
           Window stage=mnClose.getParentPopup().getScene().getWindow();
           stage.hide();
       }

    }
    @FXML
    void initialize() throws IOException {

        initData();
        refresh();
        tblViewEmployee.refresh();

    }

   private void editCurrentUser() {
           Stage stage =new Stage();
           try {
               FXMLLoader loader= new FXMLLoader(getClass().getResource("/layouts/employeeEditForm.fxml"));
                loader.load();
                stage.setScene(new Scene(loader.getRoot()));

                EmployeeEditForm controller=loader.getController();
                Employee employee=tblViewEmployee.getSelectionModel().getSelectedItem();
                controller.initData(stage,employee);
               stage.setOnCloseRequest(event -> {
                   try {
                        refresh();
                        tblViewEmployee.refresh();
                    } catch (IOException e) {
                        e.printStackTrace();
                   }
               });


           } catch (IOException e) {
               e.printStackTrace();
           }
            stage.show();
        }



    private void initData() {
       colmNum.setCellValueFactory(new PropertyValueFactory<>("num"));
        colmName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        colmPosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        colmId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colmActive.setCellValueFactory(new PropertyValueFactory<>("active"));


   }
    public void refresh() throws IOException {

        ObservableList<Employee> observableList= FXCollections.observableList(HttpHelper.INSTANCE.getEmployeeList());
        tblViewEmployee.setItems(observableList);

    }

}
