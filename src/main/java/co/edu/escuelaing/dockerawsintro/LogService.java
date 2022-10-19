/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package co.edu.escuelaing.dockerawsintro;

import static spark.Spark.*;


public class LogService {
    
    public static void main(String... args){
          port(getPort());
          staticFiles.location("/public");
          HttpConnection httPconnection = new HttpConnection();

        get("/mongo", (req, res) -> {
            res.type("application/json");
            String cadena = req.queryParams("cad");
            System.out.println("LLEGA A /MONGO");
            System.out.println(cadena);
            return httPconnection.mongodb(cadena);
        });
        }


        private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 1234;
    }
    
}
