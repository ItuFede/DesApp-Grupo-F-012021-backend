package ar.edu.unq.desapp.grupof012021.backenddesappapi.service;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public interface FirebaseService {
    Object post(String collectionName, HashMap hashmap) throws ExecutionException, InterruptedException;

    Object get(String collectionName) throws ExecutionException, InterruptedException;
}
