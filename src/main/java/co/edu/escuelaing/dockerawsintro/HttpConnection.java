package co.edu.escuelaing.dockerawsintro;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpConnection {

    private static final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws IOException, IOException {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://lauraval19:1234@basesmongo.qimhmk2.mongodb.net/?retryWrites=true&w=majority");


        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();


        MongoClient mongoClient = MongoClients.create(settings);

        List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
        MongoDatabase database = mongoClient.getDatabase("basesmongo");
        databases.forEach(db -> System.out.println(db.toJson()));

        MongoCollection<Document> customers = database.getCollection("customer");

        //Obtiene un iterable
        FindIterable<Document> iterable = customers.find();
        MongoCursor<Document> cursor = iterable.iterator();

        //Recorre el iterador obtenido del iterable
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }

        Document customer = new Document("_id", new ObjectId("234567890"));
        customer.append("firstName", "Laura");
        customer.append("lastName", "Alvarado");

        customers.insertOne(customer);



    }






    }
