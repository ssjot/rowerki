package pl.rowerki.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "work_day")
public class WorkDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Long workDayId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private User employee;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column
    @DateTimeFormat(pattern = "DD-MM-YYYY")
    private LocalDate date;

    @DateTimeFormat(pattern = "HH:mm:ss")
    @Column
    private LocalTime startTime;

    @DateTimeFormat(pattern = "HH:mm:ss")
    @Column
    private LocalTime endTime;

    @Column
    private boolean ended;

}
