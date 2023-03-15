package com.wergnet.wergnetoil.api.util.converter;

import javax.persistence.AttributeConverter;

public class StringTrimConverter implements AttributeConverter<String, String> {
    @Override
    public String convertToDatabaseColumn(String attribute) {
        return attribute != null ? attribute.trim() : attribute;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData != null ? dbData.trim() : dbData;
    }
}
