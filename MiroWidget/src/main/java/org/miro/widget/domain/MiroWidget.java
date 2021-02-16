package org.miro.widget.domain;

import lombok.Data;

import java.time.LocalDate;


@Data
public class MiroWidget {
    private String uuid;
    private Double width;
    private Double height;
    private Coordinate coordinate;
    private LocalDate lastModificationDate;
}
