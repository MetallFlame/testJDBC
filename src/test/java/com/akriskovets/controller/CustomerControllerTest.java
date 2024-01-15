package com.akriskovets.controller;

import com.akriskovets.dao.CustomerDao;
import com.akriskovets.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerDao customerDao;

    @InjectMocks
    private CustomerController customerController;

    @Test
    public void testUpdateCustomer() {
        Model model = mock(Model.class);
        Customer customer = new Customer();

        String result = customerController.updateCustomer(model, customer);

        verify(customerDao, times(1)).update(customer);
        assertEquals("message", result);
        verify(model, times(1)).addAttribute(eq("message"), eq("customer updated!"));
    }

    @Test
    public void testDeleteCustomer() {
        Model model = mock(Model.class);
        Integer customerId = 1;

        String result = customerController.deleteCustomer(model, customerId);

        verify(customerDao, times(1)).delete(customerId);
        assertEquals("message", result);
        verify(model, times(1)).addAttribute(eq("message"), eq("customer deleted!"));
    }

    @Test
    public void testCreateCustomer() {
        Model model = mock(Model.class);
        String customerName = "John Doe";

        String result = customerController.createCustomer(model, customerName);

        verify(customerDao, times(1)).save(customerName);
        assertEquals("message", result);
        verify(model, times(1)).addAttribute(eq("message"), eq("customer created!"));
    }

    @Test
    public void testCustomerList() {
        Model model = mock(Model.class);
        when(customerDao.list()).thenReturn(Collections.emptyList());

        String result = customerController.customerList(model);

        assertEquals("customer", result);
        verify(model, times(1)).addAttribute(eq("customerList"), any());
        verify(customerDao, times(1)).list();
    }
}
