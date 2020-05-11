package com.BD.MZS.Article.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    @NotNull(message = "Name cannot be null.")
    private String Author;
    @NotNull(message = "Title cannot be null.")
    private String Title;
    @NotBlank
    private Date dateOfCreate;
    @NotBlank
    private Date dateOfModify;
    @NotNull(message = "Article cannot be null.")
    private String Article;
    @NotBlank
    private static int ISBN=0;

    public ArticleDTO(String Author, String Title, String Article){
        this.Author = Author;
        this.Title = Title;
        this.dateOfCreate = new Date();
        this.dateOfModify = new Date();
        this.Article = Article;
        this.ISBN +=1;
    }

    public int getISBN(){
        return ISBN;
    }

}
