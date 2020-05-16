package com.BD.MZS.Article.repo;

import com.BD.MZS.Article.controller.dto.ArticleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleRepository {
    void save(ArticleDTO articleDTO);

    void deleteByID(int isbn);

    ArticleDTO getByID(int isbn);

    void modByID(ArticleDTO articleDTO);

    Page<ArticleDTO> findPaginated(Pageable pageable, String article, String filter, String ascOrDesc);
}
