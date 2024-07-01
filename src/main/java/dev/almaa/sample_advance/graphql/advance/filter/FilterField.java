package dev.almaa.sample_advance.graphql.advance.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import lombok.Data;

@Data
public class FilterField {
    private String operator;
    private String value;

    public Predicate generateCriteria(CriteriaBuilder builder, Path<Integer> field, Path<String> fieldS){
        try {
            int v = Integer.parseInt(value);
            switch (operator) {
                case "lt": return builder.lt(field, v);
                case "le": return builder.le(field, v);
                case "gt": return builder.gt(field, v);
                case "ge": return builder.ge(field, v);
                case "eq": return builder.equal(field, v);
            }
        } catch (NumberFormatException e) {
            switch (operator) {
                case "endsWith": return builder.like(fieldS, "%" + value);
                case "startsWith": return builder.like(fieldS, value + "%");
                case "contains" : return builder.like(fieldS, "%" + value + "%");
                case "eq": return builder.equal(fieldS, value);

            }
        }
    }
}
