package com.akriskovets.controller;

import com.akriskovets.dao.PositionDao;
import com.akriskovets.model.Position;
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
public class PositionControllerTest {

    @Mock
    private PositionDao positionDao;

    @InjectMocks
    private PositionController positionController;

    @Test
    public void testUpdatePosition() {
        Model model = mock(Model.class);
        Position position = new Position();
        position.setId(1L);
        position.setName("director");

        String result = positionController.updatePosition(model, position);

        assertEquals("message", result);
        verify(positionDao, times(1)).update(position);
        verify(model, times(1)).addAttribute(eq("message"), eq("position updated!"));
    }

    @Test
    public void testDeletePosition() {
        Model model = mock(Model.class);
        Integer positionId = 1;

        String result = positionController.deletePosition(model, positionId);

        assertEquals("message", result);
        verify(positionDao, times(1)).delete(positionId);
        verify(model, times(1)).addAttribute(eq("message"), eq("position deleted!"));
    }

    @Test
    public void testCreatePosition() {
        Model model = mock(Model.class);
        String positionName = "Software Engineer";

        String result = positionController.createPosition(model, positionName);

        assertEquals("message", result);
        verify(positionDao, times(1)).save(positionName);
        verify(model, times(1)).addAttribute(eq("message"), eq("position created!"));
    }

    @Test
    public void testPositionList() {
        Model model = mock(Model.class);
        when(positionDao.list()).thenReturn(Collections.emptyList());

        String result = positionController.positionList(model);

        assertEquals("position", result);
        verify(model, times(1)).addAttribute(eq("positionList"), any());
        verify(positionDao, times(1)).list();
    }
}
