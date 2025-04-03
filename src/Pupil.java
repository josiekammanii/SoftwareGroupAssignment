spring.data.mongodb.uri=mongodb://localhost:27017/appdbs
import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class pupil {
    public static void main(String[] args ) {

        // Replace the placeholder with your MongoDB deployment's connection string
        String uri = "<mongodb://localhost:27017/appdbs>";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("appdbs");
            MongoCollection<Document> collection = database.getCollection("pupils");

            Document doc = collection.find(eq("pupil", "Suzanna D")).first();
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }
        }
}
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
