package com.unirem.news_service.service;

import com.unirem.news_service.repository.NewRepository;
import com.unirem.news_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
    @Autowired
    private NewRepository newRepository;

    @Autowired
    private UserRepository userRepository;
}
