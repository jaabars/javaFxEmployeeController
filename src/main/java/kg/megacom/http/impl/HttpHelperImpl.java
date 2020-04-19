package kg.megacom.http.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.*;
import kg.megacom.http.HttpHelper;
import kg.megacom.models.Employee;
import kg.megacom.models.Position;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HttpHelperImpl implements HttpHelper  {
   private ObjectMapper objectMapper=new ObjectMapper();
   private OkHttpClient okHttpClient=new OkHttpClient();

   public void postEmployee(Employee employee) throws IOException {
       RequestBody requestBody=RequestBody.create(MediaType.parse("application/json"),
                objectMapper.writeValueAsString(employee));
      Request request=new Request.Builder().addHeader("Content-type","application/json")
               .url("http://localhost:8080/user/save")
                .post(requestBody).build();
        Response response=okHttpClient.newCall(request).execute();
       if(response.isSuccessful()){
            employee=objectMapper.readValue(response.body().string(), Employee.class);
           System.out.println(employee);
       }//http://localhost:8080/lot/save


   }

    public Employee getEmployeeById(Long id) throws IOException {
        Request request=new Request.Builder().addHeader("Content-type","application/json")
            .url("http://localhost:8080/user/getById?id").build();
        Response response=okHttpClient.newCall(request).execute();
        int code=response.code();
        System.out.println(code);
        Employee employee= new Employee();
        if(response.isSuccessful()){
            // System.out.println(response.body().string());
            employee=objectMapper.readValue(response.body().string(), Employee.class);
            System.out.println(employee);
            return employee;
        }else {
            System.out.println("Произошла системная ошибка!!!");
        }
        return employee;



    }

    public void postPosition(Position position) throws IOException {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json"),objectMapper.writeValueAsString(position));
        Request request=new Request.Builder().addHeader("Content-type","application/json")
                .url("http://localhost:8080/position/save")
                .post(requestBody).build();
       Response response=okHttpClient.newCall(request).execute();
        if(response.isSuccessful()){
            position=objectMapper.readValue(response.body().string(),Position.class);
           System.out.println(position);
        }

    }

  public Position getPositionById(Long id) throws IOException {
       Request request=new Request.Builder().addHeader("Content-type","application/json")
                .url("http://localhost:8080/position/getById?id").build();
       Response response=okHttpClient.newCall(request).execute();
       int code=response.code();
       System.out.println(code);
       Position position= new Position();
        if(response.isSuccessful()){
            // System.out.println(response.body().string());
         position=objectMapper.readValue(response.body().string(), Position.class);
            System.out.println(position);
            return position;
        }else {
            System.out.println("Произошла системная ошибка!!!");
        }
        return position;
    }

    public List<Position> getPositionList() throws IOException {
    Request request=new Request.Builder().addHeader("Content-type","application/json")
             .url("http://localhost:8080/position/get").build();
     Response response=okHttpClient.newCall(request).execute();
     int code=response.code();
     if (code!=200){
         try {
            throw new Exception();
         } catch (Exception e) {
             e.printStackTrace();
             System.out.println("Ошибка нет соединения с сервером");
        }
     }
    List<Position> positionList= new ArrayList<>();
    if(response.isSuccessful()){
         positionList=objectMapper.readValue(response.body().string(), new TypeReference<List<Position>>() {
            @Override
             public Type getType() {
                return super.getType();
             }
        });

        }

        return positionList;

     }

   @Override
   public List<Employee> getEmployeeList() throws IOException {
       Request request=new Request.Builder().addHeader("Content-type","application/json")
               .url("http://localhost:8080/user/list").build();
        Response response=okHttpClient.newCall(request).execute();
        int code=response.code();
/*        if(code!=200){
           try {
              throw new Exception();
          } catch (Exception e) {
               e.printStackTrace();
                System.out.println("Нет соединения");
            }
       }*/
        List<Employee> employeeList=new ArrayList<>();
        if (response.isSuccessful()){
            employeeList=objectMapper.readValue(response.body().string(), new TypeReference<List<Employee>>() {

               @Override
                public Type getType() {
                    return super.getType();
              }
            });
            return employeeList;

        }
       return employeeList;
    }
    public void updateEmployee(Employee employee) throws IOException {
        RequestBody requestBody=RequestBody.create(MediaType.parse("application/json"),
                objectMapper.writeValueAsString(employee));
       Request request=new Request.Builder().addHeader("Content-type","application/json")
               .url("http://localhost:8080/user/update")
               .put(requestBody).build();
        Response response=okHttpClient.newCall(request).execute();
        if(response.isSuccessful()){
           employee=objectMapper.readValue(response.body().string(), Employee.class);
            System.out.println(employee);
        }


    }

}

