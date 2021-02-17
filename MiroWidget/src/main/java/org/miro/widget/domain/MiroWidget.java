package org.miro.widget.domain;

import lombok.Data;


@Data
public class MiroWidget {
    private String uuid;
    private Double width;
    private Double height;
    private Coordinate coordinate;
    private String lastModificationDate;
}
