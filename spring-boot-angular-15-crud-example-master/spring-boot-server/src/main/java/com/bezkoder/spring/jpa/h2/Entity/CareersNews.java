package com.bezkoder.spring.jpa.h2.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class CareersNews {
    @Id
    @GeneratedValue
    public Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "backgroundColor")
    private String backgroundColor;

    @Column(name = "textColor")
    private String textColor;

    @Column(name = "startDate")
    private LocalDateTime startDate;

    @Column(name = "endDate")
    private LocalDateTime endDate;

    @Column(name = "labelEnabled")
    private boolean isLabelEnabled;

}