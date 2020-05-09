package com.BD.MZS.Article.repo;

import com.BD.MZS.Article.controller.dto.ArticleDTO;

import java.util.List;

public interface ArticleRepository {
    void save(ArticleDTO articleDTO);
    List<ArticleDTO> getAll();
}
