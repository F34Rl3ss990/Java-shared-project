package com.BD.MZS.Article.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor

public class ArticleDTO {
    @NotNull(message = "Name cannot be null.")
    private String Author;
    @NotNull(message = "Title cannot be null.")
    private String Title;

    private Date dateOfCreate;

    private Date dateOfModify;

    @NotNull(message = "Article cannot be null.")
    private String Article;

    private int ISBN=0;

  public ArticleDTO(String Author, String Title, String Article){
        this.Author = Author;
        this.Title = Title;
        this.dateOfCreate = new Date();
        this.dateOfModify = new Date();
        this.Article = Article;
        this.ISBN++;
    }
    public ArticleDTO(String author, String Title,  Date dateOfModify, Date dateOfCreate, String Article, int ISBN){
        this.Author = author;
        this.Title = Title;
        this.dateOfCreate = new Date();
        this.dateOfModify = new Date();
        this.Article = Article;
        this.ISBN++;
    }

}
