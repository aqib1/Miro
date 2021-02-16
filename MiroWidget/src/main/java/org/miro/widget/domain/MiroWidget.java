package org.miro.widget.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
public class MiroWidget {
    private UUID uuid;
    private Double width;
    private Double height;
    private Coordinate coordinate;
    private LocalDate lastModificationDate;
}
