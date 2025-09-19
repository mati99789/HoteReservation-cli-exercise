package customer.application;

import java.util.List;

public interface CustomerQueryUseCase {
    List<CustomerDto> allCustomers();
}
