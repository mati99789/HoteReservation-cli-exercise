package customer.application;

import customer.domain.CustomerRepository;

import java.util.List;

public class CustomerQueryUseCaseImpl implements CustomerQueryUseCase {
    private final CustomerRepository customerRepository;

    public CustomerQueryUseCaseImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDto> allCustomers() {
        return this.customerRepository.findAll().stream()
                .map(r -> new CustomerDto(r.getFirstName(), r.getLastName(), r.getEmail()))
                .toList();
    }
}
