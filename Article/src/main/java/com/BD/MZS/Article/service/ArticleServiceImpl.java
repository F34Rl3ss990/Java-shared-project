package com.BD.MZS.Article.service;

import com.BD.MZS.Article.controller.dto.ArticleDTO;
import com.BD.MZS.Article.repo.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository articleRepository;

    @Autowired
    public void setEntityRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    @Override
    public void add(ArticleDTO articleDTO) {
        articleRepository.save(articleDTO);
    }
    @Override
    public List<ArticleDTO> listAll() {
        return articleRepository.getAll();
    }

    @Override
    public void deleteByID(int isbn) {
        articleRepository.deleteByID(isbn);
    }

    @Override
    public ArticleDTO getByID(int isbn) {
       return articleRepository.getByID(isbn);
    }

    @Override
    public void modByID(ArticleDTO articleDTO) {
            articleRepository.modByID(articleDTO);
    }
    @Override
    public List<ArticleDTO> getAllSortedByAuthor(){
       return articleRepository.getAllSortedByAuthor();
    }
    @Override
    public List<ArticleDTO> getAllSortedByDateOfCreate(){
       return articleRepository.getAllSortedByDateOfCreate();
    }
    @Override
    public List<ArticleDTO> getAllSortedByDateOfModify(){
       return articleRepository.getAllSortedByDateOfModify();
    }
    @Override
    public List<ArticleDTO> getAllSortedByISBN(){
       return articleRepository.getAllSortedByISBN();
    }
    @Override
    public List<ArticleDTO> getAllSortedByTitle(){
      return articleRepository.getAllSortedByTitle();
    }
    @Override
    public List<ArticleDTO> search(String searchField){
      return  articleRepository.search(searchField);
    }
    @Override
    public Page<ArticleDTO> findPaginated(Pageable pageable,String cikk){
        return articleRepository.findPaginated(pageable, cikk);
    }




}
