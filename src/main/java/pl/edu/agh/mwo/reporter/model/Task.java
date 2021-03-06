package pl.edu.agh.mwo.reporter.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Task {
    private final String name;
    private final LocalDate date;
    private final BigDecimal hours;
    private final String projectName;

    public Task(String name, LocalDate date, BigDecimal hours, String projectName) {
        this.name = name;
        this.date = date;
        this.hours = hours;
        this.projectName = projectName;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public String getProjectName() {
        return projectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(name, task.name) &&
                Objects.equals(date, task.date) &&
                Objects.equals(hours, task.hours) &&
                Objects.equals(projectName, task.projectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, hours, projectName);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", date=" + date +
                ", hours=" + hours +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
