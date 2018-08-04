package jpa.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import utils.PermissionsLevel;

/**
 *
 * @author Rene Vera Apale
 */
@Converter(autoApply = true)
public class PermissionsLevelConverter implements AttributeConverter<PermissionsLevel, Integer>{

    @Override
    public Integer convertToDatabaseColumn(PermissionsLevel attribute) {
        if (attribute != null)
            return attribute.getLevel();
        return null;
    }

    @Override
    public PermissionsLevel convertToEntityAttribute(Integer dbData) {
        if (dbData != null)
            return PermissionsLevel.fromIntegerLevel(dbData);
        return null;
    }
}