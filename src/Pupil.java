spring.data.mongodb.uri=mongodb://localhost:27017/appdbs
public class Pupil {

    public String name;
    public LocalDate dateOfBirth;
    private Integer CohortId ;

    public Pupil(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public void findPupilbyName(){

    }




}
