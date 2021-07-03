package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Service
public class FirebaseInitializer {
    @PostConstruct
    public void initialize() {
        try {
            FileInputStream refreshToken = new FileInputStream("./../../resources/firebaseServiceAccount.json");

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
