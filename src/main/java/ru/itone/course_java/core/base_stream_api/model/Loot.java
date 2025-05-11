package ru.itone.course_java.core.base_stream_api.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Loot {

    private String name;
    private LootType type;
    private int weight;
}
