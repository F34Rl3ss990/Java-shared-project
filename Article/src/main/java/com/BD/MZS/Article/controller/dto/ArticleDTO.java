package com.BD.MZS.Article.controller.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
public class ArticleDTO {
    @NotBlank
    private String Author;
    @NotBlank
    private String Title;
    @NotBlank
    private Date dateOfCreate;
    @NotBlank
    private Date dateOfModify;
    @NotBlank
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
