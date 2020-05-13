package com.BD.MZS.Article.repo;

import com.BD.MZS.Article.controller.dto.ArticleDTO;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;



import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class ArticleRepositoryImpl implements ArticleRepository {
    private  List<ArticleDTO> articles = new ArrayList<>();


    @PostConstruct
    public void init() {
        articles.add(ArticleDTO.builder().Article("sad").Title("dsa").Author("asd").dateOfCreate(new Date()).dateOfModify(new Date()).ISBN(0).build());
        articles.add(ArticleDTO.builder().Article("sad").Title("dsa").Author("asd").dateOfCreate(new Date()).dateOfModify(new Date()).ISBN(1).build());
        articles.add(ArticleDTO.builder().Article("sad").Title("dsa").Author("asd").dateOfCreate(new Date()).dateOfModify(new Date()).ISBN(2).build());
        articles.add(ArticleDTO.builder().Article("sad").Title("dsa").Author("asd").dateOfCreate(new Date()).dateOfModify(new Date()).ISBN(3).build());
        articles.add(ArticleDTO.builder().Article("sad").Title("dsa").Author("asd").dateOfCreate(new Date()).dateOfModify(new Date()).ISBN(4).build());
        articles.add(ArticleDTO.builder().Article("sad").Title("dsa").Author("asd").dateOfCreate(new Date()).dateOfModify(new Date()).ISBN(5).build());
        articles.add(ArticleDTO.builder().Article("sad").Title("dsa").Author("asd").dateOfCreate(new Date()).dateOfModify(new Date()).ISBN(6).build());
        articles.add(ArticleDTO.builder().Article("sad").Title("dsa").Author("asd").dateOfCreate(new Date()).dateOfModify(new Date()).ISBN(7).build());
        articles.add(ArticleDTO.builder().Article("sad").Title("dsa").Author("asd").dateOfCreate(new Date()).dateOfModify(new Date()).ISBN(8).build());
        articles.add(ArticleDTO.builder().Article("sad").Title("dsa").Author("asd").dateOfCreate(new Date()).dateOfModify(new Date()).ISBN(9).build());
        articles.add(ArticleDTO.builder().Article("sad").Title("dsa").Author("asd").dateOfCreate(new Date()).dateOfModify(new Date()).ISBN(10).build());
        articles.add(ArticleDTO.builder().Article("sad").Title("dsa").Author("asd").dateOfCreate(new Date()).dateOfModify(new Date()).ISBN(11).build());
        articles.add(ArticleDTO.builder().Article("sad").Title("dsa").Author("asd").dateOfCreate(new Date()).dateOfModify(new Date()).ISBN(12).build());
        articles.add(ArticleDTO.builder().Article("sad").Title("dsa").Author("asd").dateOfCreate(new Date()).dateOfModify(new Date()).ISBN(13).build());

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
    public Page<ArticleDTO> findPaginated(Pageable pageable, String cikk) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ArticleDTO> list;
        if(cikk!=null) {
            if (this.search(cikk).size() < startItem) {
                list = Collections.emptyList();
            } else {
                int toIndex = Math.min(startItem + pageSize, this.search(cikk).size());
                list = this.search(cikk).subList(startItem, toIndex);
            }


            Page<ArticleDTO> articlePage
                    = new PageImpl<ArticleDTO>(list, PageRequest.of(currentPage, pageSize), this.search(cikk).size());

        return articlePage;
        } else {
            if (articles.size() < startItem) {
                list = Collections.emptyList();
            } else {
                int toIndex = Math.min(startItem + pageSize, articles.size());
                list = articles.subList(startItem, toIndex);
            }


            Page<ArticleDTO> articlePage
                    = new PageImpl<ArticleDTO>(list, PageRequest.of(currentPage, pageSize), articles.size());

            return articlePage;
        }
    }


}
