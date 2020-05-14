package com.BD.MZS.Article.repo;

import com.BD.MZS.Article.controller.dto.ArticleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ArticleRepository {
    void save(ArticleDTO articleDTO);
    List<ArticleDTO> getAll();
    ArticleDTO deleteByID(int isbn);
    ArticleDTO getByID(int isbn);
    void modByID(ArticleDTO articleDTO);
    List<ArticleDTO> getAllSortedByAuthor();
    List<ArticleDTO> getAllSortedByDateOfCreate();
    List<ArticleDTO> getAllSortedByDateOfModify();
    List<ArticleDTO> getAllSortedByISBN();
    List<ArticleDTO> getAllSortedByTitle();
    List<ArticleDTO> search(String searchField);
    Page<ArticleDTO> findPaginated(Pageable pageable, String cikk, String filterName, String ascDesc);
}
