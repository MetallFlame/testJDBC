package com.akriskovets.controller;

import com.akriskovets.dao.ProjectDao;
import com.akriskovets.model.Project;
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
public class ProjectControllerTest {

    @Mock
    private ProjectDao projectDao;

    @InjectMocks
    private ProjectController projectController;


    @Test
    public void testListProjects() {
        Model model = mock(Model.class);
        when(projectDao.list()).thenReturn(Collections.emptyList());

        String result = projectController.test(model);

        assertEquals("project", result);
        verify(model, times(1)).addAttribute(eq("listProject"), any());
        verify(projectDao, times(1)).list();
    }

    @Test
    public void testUpdateProject() {
        Model model = mock(Model.class);
        Project project = new Project();

        String result = projectController.updateProject(model, project);

        assertEquals("message", result);
        verify(projectDao, times(1)).update(project);
        verify(model, times(1)).addAttribute(eq("message"), eq("project updated!"));
    }

    @Test
    public void testDeleteProject() {
        Model model = mock(Model.class);
        Integer projectId = 1;

        String result = projectController.deleteProject(model, projectId);

        assertEquals("message", result);
        verify(projectDao, times(1)).delete(projectId);
        verify(model, times(1)).addAttribute(eq("message"), eq("project deleted!"));
    }

    @Test
    public void testCreateProject() {
        Model model = mock(Model.class);
        String projectName = "New Project";
        Long customerId = 1L;

        String result = projectController.createProject(model, projectName, customerId);

        assertEquals("message", result);
        verify(projectDao, times(1)).save(projectName, customerId);
        verify(model, times(1)).addAttribute(eq("message"), eq("project created!"));
    }
}
