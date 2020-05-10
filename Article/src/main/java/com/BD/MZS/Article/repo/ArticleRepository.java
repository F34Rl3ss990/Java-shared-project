package com.BD.MZS.Article.repo;

import com.BD.MZS.Article.controller.dto.ArticleDTO;

import java.util.List;

public interface ArticleRepository {
    void save(ArticleDTO articleDTO);
    List<ArticleDTO> getAll();
    void deleteByID(int isbn);
    ArticleDTO getByID(int isbn);
    void modByID(ArticleDTO articleDTO);
    List<ArticleDTO> getAllSortedByArticle();
    List<ArticleDTO> getAllSortedByAuthor();
    List<ArticleDTO> getAllSortedByDateOfCreate();
    List<ArticleDTO> getAllSortedByDateOfModify();
    List<ArticleDTO> getAllSortedByISBN();
    List<ArticleDTO> getAllSortedByTitle();
    List<ArticleDTO> search(String searchField);
}
