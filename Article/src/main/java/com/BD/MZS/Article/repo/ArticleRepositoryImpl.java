package com.BD.MZS.Article.repo;

import com.BD.MZS.Article.controller.dto.ArticleDTO;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Component
public class ArticleRepositoryImpl implements ArticleRepository {
    private final List<ArticleDTO> articles = new ArrayList<>();
    private boolean isSorted = false;
    @Override
    public List<ArticleDTO> getAll() {
        articles.sort(Comparator.comparing(ArticleDTO::getDateOfCreate));
        return articles;
    }
    @Override
    public ArticleDTO getByID(int isbn){
     for(ArticleDTO article : articles){
         if(article.getISBN()== isbn) {
             return article;
         }
     }
     return null;
    }
    @Override
    public void deleteByID(int isbn){
        for(ArticleDTO article : articles){
            if(article.getISBN() == isbn){
                articles.remove(article);
            }
        }
    }
    @Override
    public void modByID(ArticleDTO articleDTO){
        for(ArticleDTO article : articles){
            if(article.getISBN() == articleDTO.getISBN()){
                article.setDateOfModify(new Date());
                article.setArticle(articleDTO.getArticle());
            }
        }
    }

    @Override
    public void save(ArticleDTO articleDTO) {
        articles.add(articleDTO);
    }

    public List<ArticleDTO> getAllSortedByArticle() {
        if(!isSorted){
            articles.sort(Comparator.comparing(ArticleDTO::getArticle));
            isSorted = true;
            return articles;
        } else {
            articles.sort(Comparator.comparing(ArticleDTO::getArticle).reversed());
            isSorted = false;
            return articles;
        }

    }

}
