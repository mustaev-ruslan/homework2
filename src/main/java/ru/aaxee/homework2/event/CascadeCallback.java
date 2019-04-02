package ru.aaxee.homework2.event;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.util.ReflectionUtils;
import ru.aaxee.homework2.annotation.CascadeSave;

import java.lang.reflect.Field;
import java.util.Collection;

public class CascadeCallback implements ReflectionUtils.FieldCallback {

    private Object source;
    private MongoOperations mongoOperations;

    CascadeCallback(final Object source, final MongoOperations mongoOperations) {
        this.source = source;
        this.setMongoOperations(mongoOperations);
    }

    @Override
    public void doWith(final Field field) throws IllegalArgumentException, IllegalAccessException {
        ReflectionUtils.makeAccessible(field);

        if (field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(CascadeSave.class)) {
            final Object fieldValue = field.get(getSource());

            if (fieldValue != null) {
                if ((Collection.class.isAssignableFrom(field.getType()))) {
                    ReflectionUtils.makeAccessible(field);
                    Collection collection = (Collection) fieldValue;
                    for (Object element : collection) {
//                        System.out.println(element.getClass());
                        for (Field innerField : element.getClass().getDeclaredFields()) {
                            if (innerField.isAnnotationPresent(Id.class)) {
                                ReflectionUtils.makeAccessible(innerField);
                                if (innerField.get(element) == null)
                                    getMongoOperations().save(element);
                            }
                        }
                    }
                } else {
                    final FieldCallback callback = new FieldCallback();

                    ReflectionUtils.doWithFields(fieldValue.getClass(), callback);

                    getMongoOperations().save(fieldValue);
                }
            }
        }

    }

    private Object getSource() {
        return source;
    }

    public void setSource(final Object source) {
        this.source = source;
    }

    private MongoOperations getMongoOperations() {
        return mongoOperations;
    }

    private void setMongoOperations(final MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }
}
