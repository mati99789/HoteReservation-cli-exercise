package customer.infrastructure;

import customer.domain.Customer;
import customer.domain.CustomerRepository;
import validation.exceptions.ValidationException;

import java.util.*;

public class InMemoryCustomerRepository implements CustomerRepository {
    private final Map<String, Customer> customers = new HashMap<>();

    @Override
    public List<Customer> findAll() {
		return new ArrayList<>(customers.values());
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
		return Optional.ofNullable(customers.get(email));
    }

    @Override
    public Customer save(Customer customer) {
        if(customers.containsKey(customer.getEmail())) {
			throw new ValidationException("Customer with email " + customer.getEmail() + " exist");
        }

		customers.put(customer.getEmail(), customer);
		return customer;
    }

    @Override
    public void deleteByEmail(String email) {
		customers.remove(email);
    }
}
