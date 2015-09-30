package com.tradedoubler.application.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tradedoubler.application.error.ErrorCode;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Created by abdal on 2015-07-07.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GreetingResponse {

    private long id;
    private String content;
    private String greetingText;
    private  ErrorCode errorCode;
    private String message;

    public GreetingResponse(){
    }

    public GreetingResponse(long id, String content, String greetingText) {
        this.id = id;
        this.content = content;
        this.greetingText = greetingText;
    }

    public GreetingResponse(ErrorCode errorCode, String message){
        this.errorCode = errorCode;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getContent() {
        return content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getText() {
        return greetingText;
    }

    @Override
    public String toString()
    {
        return new ReflectionToStringBuilder(this).reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
