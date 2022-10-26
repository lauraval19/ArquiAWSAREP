package co.edu.escuelaing.dockerawsintro;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;


import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MongoConnection {

    private static final String USER_AGENT = "Mozilla/5.0";
    private String url = "mongodb://localhost:27017";

    public List<Document> mongodb(String user) throws IOException, IOException {
        String connstr = url;

        ConnectionString connectionString = new ConnectionString(connstr);

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();


        MongoClient mongoClient = MongoClients.create(settings);

        List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
        MongoDatabase database = mongoClient.getDatabase("message");

        databases.forEach(db -> System.out.println(db.toJson()));

        MongoCollection<Document> cadenas = database.getCollection("cadena");

        //Obtiene un iterable
        FindIterable<Document> iterable = cadenas.find();
        MongoCursor<Document> cursor = iterable.iterator();



        Document cadenan = new Document();
        Date date = Date.from(Instant.now());
        cadenan.append("Cadena", user);
        cadenan.append("Fecha",date);

        cadenas.insertOne(cadenan);

        List<Document> logs = new ArrayList<>();
        //Recorre el iterador obtenido del iterable
        while (cursor.hasNext()) {
            System.out.println("desdee el iterador------->");
            System.out.println(cursor.next());
            if(logs.size() <= 10){
                logs.add(cursor.next());
            }
        }

        return logs;
    }

}
