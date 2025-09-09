package repo.customer;

import model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    List<Customer> findAll();
    Optional<Customer> findByEmail(String email);
    Customer save(Customer customer);
    void delete(String email);
}
