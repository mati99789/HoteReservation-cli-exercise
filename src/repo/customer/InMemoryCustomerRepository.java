package repo.customer;

import model.Customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryCustomerRepository implements CustomerRepository {
    private final Map<String, Customer> customers = new HashMap<>();

    @Override
    public List<Customer> findAll() {
        return List.of();
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public void delete(String email) {

    }
}
