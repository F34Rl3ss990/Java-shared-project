package com.BD.MZS.Article.repo;

import com.BD.MZS.Article.controller.dto.ArticleDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleRepositoryImpl implements ArticleRepository {
    private final List<ArticleDTO> articles = new ArrayList<>();
    @Override
    public List<ArticleDTO> getAll() {
        return articles;
    }

    @Override
    public void save(ArticleDTO entityDto) {
        articles.add(entityDto);
    }
}
