package webservice;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import persistence.CustomerRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @GetMapping("/bulkcreate")
    public String bulkcreate(){
        repository.save(new Customer("Rajesh", "Bhojwani"));

        repository.saveAll(Arrays.asList(new Customer("Salim", "Khan")
                , new Customer("Rajesh", "Parihar")
                , new Customer("Rahul", "Dravid")
                , new Customer("Dharmendra", "Bhojwani")));

        return "Customers are created";
    }

    @GetMapping("/findall")
    public List<Customer> findAll() {
        List<Customer> customers = repository.findAll();
        return customers;
    }
}