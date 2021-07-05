package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import com.google.cloud.firestore.QueryDocumentSnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface FirebaseService {
    Object post(String collectionName, HashMap hashmap) throws ExecutionException, InterruptedException;

    List<QueryDocumentSnapshot> get(String collectionName) throws ExecutionException, InterruptedException;
}
