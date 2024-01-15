package com.akriskovets.dao;

import com.akriskovets.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerDaoTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private CustomerDao customerDao;

    @Test
    public void testListCustomers() {
        List<Customer> expectedCustomers = Arrays.asList(
                new Customer(1L, "Customer1"),
                new Customer(2L, "Customer2")
        );

        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(expectedCustomers);

        List<Customer> actualCustomers = customerDao.list();

        assertEquals(expectedCustomers, actualCustomers);

        verify(jdbcTemplate, times(1)).query(anyString(), any(RowMapper.class));
    }


    @Test
    public void testGetCustomer() {
        int customerId = 1;
        Customer expectedCustomer = new Customer((long) customerId, "Customer1");
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(RowMapper.class)))
                .thenReturn(expectedCustomer);

        Customer actualCustomer = customerDao.get(customerId);

        assertEquals(expectedCustomer, actualCustomer);

        verify(jdbcTemplate, times(1)).queryForObject(eq("SELECT * FROM customer WHERE id = ?"),
                any(Object[].class), any(RowMapper.class));
    }



    @Test
    public void testDeleteCustomer() {
        int customerId = 1;

        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            assertEquals("DELETE FROM customer WHERE id=?", args[0]);
            assertEquals(1, args[1]);
            return null;
        }).when(jdbcTemplate).update(anyString(), anyInt());

        customerDao.delete(customerId);

        verify(jdbcTemplate, times(1)).update(eq("DELETE FROM customer WHERE id=?"), eq(1));
    }
}
