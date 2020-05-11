package com.BD.MZS.Article.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class ArticleDTO {
    @NotNull(message = "Name cannot be null.")
    private String Author;
    @NotNull(message = "Title cannot be null.")
    private String Title;

    private Date dateOfCreate;

    private Date dateOfModify;

    @NotNull(message = "Article cannot be null.")
    private String Article;

    private int ISBN=counter;
    private static int counter=0;
    public static void counter(){
        counter++;
    }
    public ArticleDTO(){
        this.dateOfModify = new Date();
        this.dateOfCreate = new Date();
    }


}
