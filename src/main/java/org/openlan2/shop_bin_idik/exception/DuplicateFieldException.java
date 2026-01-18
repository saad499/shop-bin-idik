package org.openlan2.shop_bin_idik.exception;

import java.util.List;
import java.util.stream.Collectors;

public class DuplicateFieldException extends RuntimeException {
    
    private final List<String> fields;
    
    public DuplicateFieldException(List<String> fields) {
        super(createMessage(fields));
        this.fields = fields;
    }
    
    private static String createMessage(List<String> fields) {
        if (fields.size() == 1) {
            return fields.get(0) + " existe déjà";
        } else {
            return fields.stream().collect(Collectors.joining(", ")) + " existent déjà";
        }
    }
    
    public List<String> getFields() {
        return fields;
    }
}
