package br.com.grimoire.apirestrpgv02.model.exception;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError implements Serializable {

    private String error;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Date currentDate;
    private Integer status;
    private String path;

    public StandardError(String error, Integer status, String message, Date currentDate) {
        this.error = error;
        this.status = status;
        this.message = message;
        this.currentDate = currentDate;
    }
    
}
