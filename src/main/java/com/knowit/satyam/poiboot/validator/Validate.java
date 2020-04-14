package com.knowit.satyam.poiboot.validator;

import java.util.List;

public interface Validate {
    boolean validate();

    List<String> getErrors();
}
