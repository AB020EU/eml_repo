package co.absa.eml.clients;

import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import co.absa.eml.restinteractionpoints.RestInteractionPoints;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Component
public class Connections {

    @Autowired
    RestInteractionPoints restInteractionPoints;


    public Connection getNucleusConnectionS2(String applicationNumber) {

        Response res= restInteractionPoints.get("/get/env/byid/"+applicationNumber);


        Response envName= restInteractionPoints.get("/get/env/name/byName/"+res.body().prettyPrint());



        Connection connection = null;
        Statement statement = null;
        try {

            if( envName.body().prettyPrint().equalsIgnoreCase("SIT")){
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://oss-vip-0932.corp.dsarena.com:5432/app_account",
                        "readonly", "READ0nly");
                System.out.println("Opened database successfully");


            }else if(  envName.body().prettyPrint().equalsIgnoreCase("UAT")){
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://oss-vip-0933.corp.dsarena.com:5433/app_account",
                        "readonly", "READ0nly");
                System.out.println("Opened database successfully");


            }if( envName.body().prettyPrint().equalsIgnoreCase("Hot Fix")){
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://oss-vip-4539.corp.dsarena.com:5433/app_account",
                        "readonly", "READ0nly");
                System.out.println("Opened database successfully");


            }
            return connection;

        } catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return connection;
        }


    }
    public Connection getNucleusConnectionS3(String applicationNumber) {

        Response res= restInteractionPoints.get("/get/env/byid/"+applicationNumber);


        Response envName= restInteractionPoints.get("/get/env/name/byName/"+res.body().prettyPrint());



        Connection connection = null;
        Statement statement = null;
        try {

            if( envName.body().prettyPrint().equalsIgnoreCase("SIT")){
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://oss-vip-0933.corp.dsarena.com:5433/app_account",
                        "readonly", "READ0nly");
                System.out.println("Opened database successfully");


            }else if(  envName.body().prettyPrint().equalsIgnoreCase("UAT")){
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://oss-vip-0933.corp.dsarena.com:5433/app_account",
                        "readonly", "READ0nly");
                System.out.println("Opened database successfully");


            }if( envName.body().prettyPrint().equalsIgnoreCase("Hot Fix")){
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://oss-vip-4539.corp.dsarena.com:5433/app_account",
                        "readonly", "READ0nly");
                System.out.println("Opened database successfully");

            }
            return connection;

        } catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return connection;
        }


    }
    public  Connection getNucleusConnectionProcessEngineS2(String applicationNumber) {

        Response res= restInteractionPoints.get("/get/env/byid/"+applicationNumber);


        Response envName= restInteractionPoints.get("/get/env/name/byName/"+res.body().prettyPrint());


        Connection connection = null;
        Statement statement = null;
        try {

            if(envName.body().prettyPrint().equalsIgnoreCase("SIT")){
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://oss-vip-0932.corp.dsarena.com:5433/app_account",
                        "process_readonly", "Pr0C355_R34D0N_Y_69");
                System.out.println("Opened database successfully");
            }else if(envName.body().prettyPrint().equalsIgnoreCase("UAT")){
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://oss-vip-0933.corp.dsarena.com:5433/app_account",
                        "process_readonly", "F2wgpJD8M1");
                System.out.println("Opened database successfully");

            }if(envName.body().prettyPrint().equalsIgnoreCase("Hot Fix")){
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection("jdbc:postgresql://oss-vip-0933.corp.dsarena.com:5433/app_account",
                        "process_readonly", "F2wgpJD8M1");
                System.out.println("Opened database successfully");

            }





        } catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        return connection;
    }
    public  Connection getNucleusConnection() {
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://oss-vip-0932.corp.dsarena.com:5432/app_account",
                    "readonly", "READ0nly");
            System.out.println("Opened database successfully");
        } catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return connection;
    }
    public Connection getGeospatialConnection() {

        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://oss-vip-0932.corp.dsarena.com:5432/app_account",
                    "geospatial", "#K5SOL!a");
            System.out.println("Opened database successfully");
        } catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return connection;
    }
}
