package com.akriskovets.model;

import lombok.Data;

import java.util.Objects;

@Data
public class EmployeeProject {

    public EmployeeProject(Long employeeId, Long projectId) {
        this.employeeId = employeeId;
        this.projectId = projectId;
    }

    private Long employeeId;
    private Long projectId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeProject that = (EmployeeProject) o;
        return Objects.equals(employeeId, that.employeeId) && Objects.equals(projectId, that.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, projectId);
    }
}
