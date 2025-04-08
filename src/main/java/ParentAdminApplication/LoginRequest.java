package ParentAdminApplication;

import java.time.LocalDate;

public class LoginRequest {
    private String name;
    private LocalDate DOB;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDOB(LocalDate DOB){
        this.DOB = DOB;
    }

    public LocalDate getDOB(){
        return DOB;
    }

}
