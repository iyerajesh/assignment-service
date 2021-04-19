package com.xylia.domain.payments.assignment.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class Pokemon {

    private int pokeId;
    private String name;
    private double height;
    private double weight;
    private String happiness;
}
