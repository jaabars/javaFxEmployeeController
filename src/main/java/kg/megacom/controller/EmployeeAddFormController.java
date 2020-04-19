package kg.megacom.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Window;
import kg.megacom.http.HttpHelper;
import kg.megacom.models.Employee;
import kg.megacom.models.Position;

public class EmployeeAddFormController {
    private  int num=1;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtName;


    @FXML
    private ComboBox<Position> cmbBoxPosition;

    @FXML
    private CheckBox chkboxActive;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    @FXML
    void onButtonClicked(ActionEvent event) {
        if (event.getSource().equals(btnSave)){
            onSaveButtonClicked();
       }else {
           Window stage=btnCancel.getScene().getWindow();
           stage.hide();
       }

    }


   private void onSaveButtonClicked() {
       Employee employee=new Employee();
        employee.setEmpName(txtName.getText());
        employee.setActive(chkboxActive.isSelected());
        employee.setPosition(cmbBoxPosition.getSelectionModel().getSelectedItem());
        employee.setNum(num++);



      try {
           HttpHelper.INSTANCE.postEmployee(employee);


        } catch (IOException e) {
           e.printStackTrace();
        }

    }

    @FXML
    void initialize() throws IOException {
        initComboBox();

    }

    private void initComboBox() {
        try {

            List<Position> positionList=HttpHelper.INSTANCE.getPositionList();
            cmbBoxPosition.setCellFactory(param -> new ListCell<Position>(){
                @Override
                protected void updateItem(Position item,boolean empty){
                    super.updateItem(item,empty);
                    if(item!=null&& !empty){
                        setText(item.getPosition());
                    }else{
                        setText(null);
                    }
                }
            });
            cmbBoxPosition.setItems(FXCollections.observableArrayList(positionList));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

