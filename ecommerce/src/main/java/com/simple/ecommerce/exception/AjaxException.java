package com.simple.ecommerce.exception;

import java.text.MessageFormat;

public class AjaxException extends RuntimeException{
    

    public AjaxException(String message) {
        super(message);
    }

    public AjaxException(String message, String variable) {
        this(MessageFormat.format("This is Null >> [{0}]", variable));
    }

    public AjaxException(String message, Throwable cause) {
        super(message, cause);
    }

}
