package com.BD.MZS.Article.repo;

import com.BD.MZS.Article.controller.dto.ArticleDTO;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class ArticleRepositoryImpl implements ArticleRepository {
    private  List<ArticleDTO> articles = new ArrayList<>();

    @PostConstruct
    public void init() {
        articles.add(ArticleDTO.builder().Article("ZsakFos").Title("FosZsák").Author("buzigeci").dateOfCreate(new Date()).dateOfModify(new Date()).ISBN(0).build());
        articles.add(ArticleDTO.builder().Article("ZsakFos").Title("FosZsák").Author("buzigeci").dateOfCreate(new Date()).dateOfModify(new Date()).ISBN(1).build());
    }

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
                break;
            }
        }
    }
    @Override
    public void modByID(ArticleDTO articleDTO){
        for(ArticleDTO article : articles){
            if(article.getISBN() == articleDTO.getISBN()){
                article.setArticle(articleDTO.getArticle());
                article.setAuthor(articleDTO.getAuthor());
                article.setTitle(articleDTO.getTitle());
            }
        }
    }

    @Override
    public void save(ArticleDTO articleDTO) {
        articles.add(articleDTO);
    }

    private List<ArticleDTO> getArticleDTOS(Comparator<ArticleDTO> comparing) {
        List<ArticleDTO> articleTest = new ArrayList<>();
        for(ArticleDTO article : articles){
            articleTest.add(article);
        }
        articles.sort(comparing);
        if(!articles.equals(articleTest)){
            return articles;
        } else {
            articles.sort(comparing.reversed());
            return articles;
        }
    }
    @Override
    public List<ArticleDTO> getAllSortedByAuthor() {
        return getArticleDTOS(Comparator.comparing(ArticleDTO::getAuthor));
    }
    @Override
    public List<ArticleDTO> getAllSortedByDateOfCreate() {
        return getArticleDTOS(Comparator.comparing(ArticleDTO::getDateOfCreate));
    }
    @Override
    public List<ArticleDTO> getAllSortedByDateOfModify() {
        return getArticleDTOS(Comparator.comparing(ArticleDTO::getDateOfModify));
    }
    @Override
    public List<ArticleDTO> getAllSortedByISBN() {
        System.out.println("FDLSKfLÉFLÉKSDLÉFKSDLÉF");
        return getArticleDTOS(Comparator.comparing(ArticleDTO::getISBN));
    }
    @Override
    public List<ArticleDTO> getAllSortedByTitle() {
        return getArticleDTOS(Comparator.comparing(ArticleDTO::getTitle));
    }
    @Override
    public List<ArticleDTO> search(String searchField){
        Predicate<ArticleDTO> contain = (ArticleDTO item) -> item.toString().contains(searchField);
        // ez bővíthető azáltal h minden fieldre külön lehessen keresni
        List<ArticleDTO> hasArticleList = articles.stream().
                filter(contain).collect(Collectors.toList());
        return hasArticleList;
    }
}
