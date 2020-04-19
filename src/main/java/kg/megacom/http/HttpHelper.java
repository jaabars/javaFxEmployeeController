package kg.megacom.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import kg.megacom.http.impl.HttpHelperImpl;
import kg.megacom.models.Employee;
import kg.megacom.models.Position;

import java.io.IOException;
import java.util.List;

public interface HttpHelper {
    HttpHelper INSTANCE = new HttpHelperImpl();
    void postEmployee(Employee employee) throws IOException;
    Employee getEmployeeById(Long id) throws IOException;
    void postPosition(Position position) throws IOException;
    Position getPositionById(Long id) throws IOException;
    List<Position> getPositionList() throws IOException;
    List<Employee> getEmployeeList() throws IOException;
    void updateEmployee(Employee employee) throws IOException;


}
