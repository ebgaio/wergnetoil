package com.wergnet.wergnetoil.api.util.datas;

import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

@Service
public class FormattedDateMatcher implements DateMatcher {

    private static Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    @Override
    public boolean matches(String date) {
      return DATE_PATTERN.matcher(date).matches();
    }
}
