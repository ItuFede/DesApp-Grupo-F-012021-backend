package ar.edu.unq.desapp.grupof012021.backenddesappapi.service.implementations;

import ar.edu.unq.desapp.grupof012021.backenddesappapi.service.FirebaseService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service("firebaseService")
public class FirebaseServiceImpl implements FirebaseService {

    public Object post(String collectionName, HashMap hashmap) throws ExecutionException, InterruptedException {
        hashmap.put("millsTime", System.currentTimeMillis());
        Firestore fireDAO = FirestoreClient.getFirestore();
        ApiFuture<?> collections = fireDAO.collection(collectionName).document().set(hashmap);
        return collections.get();
    }

    public List<QueryDocumentSnapshot> get(String collectionName) throws ExecutionException, InterruptedException {
        Firestore fireDAO = FirestoreClient.getFirestore();
        return fireDAO.collection(collectionName).get().get().getDocuments();
    }
}
