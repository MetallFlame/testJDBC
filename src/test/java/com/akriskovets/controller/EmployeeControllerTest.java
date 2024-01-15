package com.akriskovets.controller;

import com.akriskovets.dao.EmployeeDao;
import com.akriskovets.model.Employee;
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
public class EmployeeControllerTest {

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void testEmployeeList() {
        Model model = mock(Model.class);
        when(employeeDao.getAll()).thenReturn(Collections.emptyList());

        String result = employeeController.employeeList(model);

        assertEquals("employee", result);
        verify(model, times(1)).addAttribute(eq("listEmployee"), any());
        verify(employeeDao, times(1)).getAll();
    }

    @Test
    public void testUpdateEmployee() {
        Model model = mock(Model.class);
        Employee employee = new Employee();

        String result = employeeController.updateEmployee(model, employee);

        assertEquals("message", result);
        verify(employeeDao, times(1)).update(employee);
        verify(model, times(1)).addAttribute(eq("message"), eq("employee updated!"));
    }

    @Test
    public void testDeleteEmployee() {
        Model model = mock(Model.class);
        Integer employeeId = 1;

        String result = employeeController.deleteEmployee(model, employeeId);

        assertEquals("message", result);
        verify(employeeDao, times(1)).delete(employeeId);
        verify(model, times(1)).addAttribute(eq("message"), eq("employee deleted!"));
    }

    @Test
    public void testCreateEmployee() {
        Model model = mock(Model.class);
        String employeeName = "John Doe";
        Long positionId = 1L;

        String result = employeeController.createPosition(model, employeeName, positionId);

        assertEquals("message", result);
        verify(employeeDao, times(1)).save(employeeName, positionId);
        verify(model, times(1)).addAttribute(eq("message"), eq("employee created!"));
    }
}
