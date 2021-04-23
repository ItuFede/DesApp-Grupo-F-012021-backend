package ar.edu.unq.desapp.grupoF012021.backenddesappapi.webservice;

import ar.edu.unq.desapp.grupoF012021.backenddesappapi.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ar.edu.unq.desapp.grupoF012021.backenddesappapi.persistence.CustomerRepository;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/customer/")
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @GetMapping("bulkcreate")
    public String bulkcreate(){
        repository.save(new Customer("Rajesh", "Bhojwani"));

        repository.saveAll(Arrays.asList(new Customer("Salim", "Khan")
                , new Customer("Rajesh", "Parihar")
                , new Customer("Rahul", "Dravid")
                , new Customer("Dharmendra", "Bhojwani")));

        return "Customers are created";
    }

    @GetMapping("findall")
    public List<Customer> findAll() {
        List<Customer> customers = repository.findAll();
        return customers;
    }

    @GetMapping("hello")
    public String helloWorld() {
        return "Hello World!!!!!";
    }
}