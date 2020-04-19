package kg.megacom.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import kg.megacom.http.HttpHelper;
import kg.megacom.models.Position;

import java.awt.*;
import java.io.IOException;

public class PositionAddFormController {

    @FXML
    private TextField txtPosition;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    @FXML
    void onButtonClicked(ActionEvent event) {
      if (event.getSource().equals(btnSave)){
            onButtonSaveClicked();     }else {
            Window stage=btnCancel.getScene().getWindow();
           stage.hide();
       }

    }

    private void onButtonSaveClicked() {
       Position position=new Position();
        position.setPosition(txtPosition.getText());
        try {
           HttpHelper.INSTANCE.postPosition(position);
       } catch (IOException e) {
            e.printStackTrace();
       }
   }
}
