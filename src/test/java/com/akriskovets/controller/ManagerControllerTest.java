package com.akriskovets.controller;

import com.akriskovets.dao.ManagerDao;
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
public class ManagerControllerTest {


    @Mock
    private ManagerDao managerDao;

    @InjectMocks
    private ManagerController managerController;

    @Test
    public void testClearFields() {
        Model model = mock(Model.class);

        String result = managerController.clearFields(model);

        assertEquals("message", result);
        verify(managerDao, times(1)).clearFields();
        verify(model, times(1)).addAttribute(eq("message"), eq("DB cleared!"));
    }

    @Test
    public void testFillTablesWithRandomData() {
        Model model = mock(Model.class);

        String result = managerController.fillTablesWithRandomData(model);

        assertEquals("message", result);
        verify(managerDao, times(1)).fillTables();
        verify(model, times(1)).addAttribute(eq("message"), eq("DB filled with random data!"));
    }

    @Test
    public void testAddEmployeeAndPosition() {
        Model model = mock(Model.class);

        String result = managerController.addEmployeeAndPosition(model);

        assertEquals("message", result);
        verify(managerDao, times(1)).addEmployeeAndPosition();
        verify(model, times(1)).addAttribute(eq("message"), eq("Employee and position added!"));
    }

    @Test
    public void testAddCustomerAndProject() {
        Model model = mock(Model.class);

        String result = managerController.addCustomerAndProject(model);

        assertEquals("message", result);
        verify(managerDao, times(1)).addCustomerAndProject();
        verify(model, times(1)).addAttribute(eq("message"), eq("Customer and project added!"));
    }

    @Test
    public void testFullInfo() {
        Model model = mock(Model.class);
        when(managerDao.getFullInfo()).thenReturn(Collections.emptyList());

        String result = managerController.fullInfo(model);

        assertEquals("full_info", result);
        verify(model, times(1)).addAttribute(eq("fullInfoList"), any());
        verify(managerDao, times(1)).getFullInfo();
    }
}
