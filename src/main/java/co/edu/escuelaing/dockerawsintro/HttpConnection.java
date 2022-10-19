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
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HttpConnection {

    private static final String USER_AGENT = "Mozilla/5.0";
    private String url = "mongodb://localhost:27017";

    public String mongodb(String user) throws IOException, IOException {
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

        //Recorre el iterador obtenido del iterable
        while (cursor.hasNext()) {
            System.out.println("desdee el iterador------->");
            System.out.println(cursor.next());
        }

        Document cadenan = new Document();
        Date date = Date.from(Instant.now());
        cadenan.put("Cadena", user);
        cadenan.put("Fecha",date);

        cadenas.insertOne(cadenan);

        List<Document> databasesn = mongoClient.listDatabases().into(new ArrayList<>());
        return databasesn.get(0).toJson();
    }

}
