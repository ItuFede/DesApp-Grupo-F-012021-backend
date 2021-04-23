package ar.edu.unq.desapp.grupoF012021.backenddesappapi.persistence;

import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.Customer;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findAll();
}
