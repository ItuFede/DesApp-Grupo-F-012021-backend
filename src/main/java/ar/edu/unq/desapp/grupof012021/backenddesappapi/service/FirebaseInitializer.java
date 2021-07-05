package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
public class FirebaseInitializer {

    @Autowired
    private Environment env;

    @PostConstruct
    public void initialize() {
        try {
            JsonObject credentials = new JsonObject();
            credentials.addProperty("type", "service_account");
            credentials.addProperty("project_id", "re-sena-api");
            credentials.addProperty("private_key_id", "25bd16984bcc88eae3661d6f61f51269cb4b61ad");
            credentials.addProperty("private_key", env.getProperty("fireadminkey"));
            credentials.addProperty("client_email", "firebase-adminsdk-uctp3@re-sena-api.iam.gserviceaccount.com");
            credentials.addProperty("client_id", "105071646795686311515");
            credentials.addProperty("auth_uri", "https://accounts.google.com/o/oauth2/auth");
            credentials.addProperty("token_uri", "https://oauth2.googleapis.com/token");
            credentials.addProperty("auth_provider_x509_cert_url", "https://www.googleapis.com/oauth2/v1/certs");
            credentials.addProperty("client_x509_cert_url", "https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-uctp3%40re-sena-api.iam.gserviceaccount.com");
//            FileInputStream refreshToken = new FileInputStream("./src/main/resources/firebaseServiceAccount.json");

            String str = credentials.toString();
            InputStream refreshToken = new ByteArrayInputStream(str.getBytes());
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .setDatabaseUrl("https://re-sena-api.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
