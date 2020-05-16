package com.BD.MZS.Article.controller.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

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
    @Length(min = 10)
    private String Author;
    @NotNull(message = "Title cannot be null.")
    @Length(min = 10)
    private String Title;
    @Setter
    private Date dateOfCreate = dateCreate;
    private Date dateOfModify;

    @NotNull(message = "Article cannot be null.")
    @Length(min = 100)
    private String Article;

    private int ISBN = counter;
    private static int counter = 0;

    public static void counter() {
        counter++;
    }

    private static Date dateCreate;

    public static Date setDateCreate(Date date) {
        return dateCreate = date;
    }

    public ArticleDTO() {
        this.dateOfModify = new Date();
    }

}
