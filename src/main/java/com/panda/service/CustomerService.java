package com.panda.service;

import com.panda.dto.CustomerDto;
import com.panda.mapper.CustomerMapper;
import com.panda.model.Customer;
import com.panda.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    /**
     * добавление заказчика
     * @param customerDto
     * @return
     */
    @Transactional
    public CustomerDto addCustomer(CustomerDto customerDto) {

        Customer customerSaved = customerMapper.toEntity(customerDto);
        customerRepository.save(customerSaved);

        return customerMapper.toDto(customerSaved);
    }

    /**
     * удаление заказчика
     * @param customerId
     */
    @Transactional
    public void deleteCustomer(UUID customerId) {

        Customer customerDelete = customerRepository.findById(customerId).orElseThrow();
        customerRepository.delete(customerDelete);
    }

    public CustomerDto getCustomerById(UUID customerId) {

        Customer entity = customerRepository.findById(customerId).orElseThrow();
        return customerMapper.toDto(entity);
    }

    public List<CustomerDto> getAllCustomers() {

        return customerRepository.findAll().stream()
                .map(customerMapper::toDto)
                .toList();
    }
}
