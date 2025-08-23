package com.unirem.news_service.repository;

import com.unirem.news_service.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewRepository extends JpaRepository<News, Long> {
}
