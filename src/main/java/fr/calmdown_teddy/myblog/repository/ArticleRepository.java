package fr.calmdown_teddy.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.calmdown_teddy.myblog.model.Article;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByTitle(String title);

    List<Article> findByContentContaining(String title);

    List<Article> findByCreatedAtAfter(LocalDateTime date);

    List<Article> findTop5ByOrderByCreatedAt(LocalDateTime date);

}
