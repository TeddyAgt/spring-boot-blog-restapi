package fr.calmdown_teddy.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.calmdown_teddy.myblog.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
