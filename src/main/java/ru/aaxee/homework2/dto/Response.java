package ru.aaxee.homework2.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Response {
    private final ResponseStatus status;
    private final String message;
    private final Object object;

    public static Response ok(String message) {
        return new Response(ResponseStatus.OK, message, null);
    }

    public static Response ok() {
        return ok(null);
    }

    public static Response error(String message) {
        return new Response(ResponseStatus.ERROR, message, null);
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static Response of(Optional optional) {
        if (optional.isPresent()) {
            return of(optional.get());
        } else {
            return ok("Object not found");
        }
    }

    public static Response of(Object object) {
        return new Response(ResponseStatus.OK, null, object);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(status + "\n");
        if (message != null) {
            string.append(message);
        }
        if (message != null && object != null) {
            string.append("\n");
        }
        if (object != null) {
            if (object instanceof Collection) {
                String separator = "";
                for (Object element : (Collection) object) {
                    string.append(separator).append(element);
                    separator = "\n";
                }
            } else {
                string.append(object);
            }
        }
        return string.toString();
    }

    private enum ResponseStatus {
        OK,
        ERROR,
    }

}
