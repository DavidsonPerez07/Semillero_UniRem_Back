package com.unirem.news_service.repository;

import com.unirem.news_service.entity.New;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewRepository extends JpaRepository<New, Long> {
}
