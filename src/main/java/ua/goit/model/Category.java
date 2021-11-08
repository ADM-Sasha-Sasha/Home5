package ua.goit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements BaseEntity<Integer> {
    private Integer id;
    private String name;
}
