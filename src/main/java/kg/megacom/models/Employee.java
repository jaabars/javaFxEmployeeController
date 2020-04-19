package kg.megacom.models;

import javafx.geometry.Pos;
import lombok.Data;

@Data
public class Employee {
    public Long id;
    private String empName;
    private boolean active;
    private Position position;
    private int num;
}
