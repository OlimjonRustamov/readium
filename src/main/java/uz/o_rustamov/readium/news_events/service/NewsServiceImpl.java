package uz.o_rustamov.readium.news_events.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.news_events.dto.NewsDto;
import uz.o_rustamov.readium.news_events.model.News;
import uz.o_rustamov.readium.news_events.repository.NewsRepository;
import uz.o_rustamov.readium.study_centre.model.StudyCentre;
import uz.o_rustamov.readium.study_centre.repository.StudyCentreRepository;
import uz.o_rustamov.readium.user.model.User;

import java.sql.Date;
import java.util.Optional;

import static uz.o_rustamov.readium.Constants.NOT_FOUND;
import static uz.o_rustamov.readium.Constants.SUCCESS;

@Service
public class NewsServiceImpl implements NewsService {

    NewsRepository newsRepository;
    StudyCentreRepository studyCentreRepository;

    public NewsServiceImpl(NewsRepository newsRepository, StudyCentreRepository studyCentreRepository) {
        this.newsRepository = newsRepository;
        this.studyCentreRepository = studyCentreRepository;
    }

    @Override
    public HttpEntity<ApiResponse> getStudyCentreNews(User user) {
        return ResponseEntity.ok(new ApiResponse(null, 200, newsRepository.findByStudyCentre_Id(user.getStudyCentre().getId())));
    }

    @Override
    public HttpEntity<ApiResponse> getSingleNews(User user, long id) {
        Optional<News> optionalNews = newsRepository.findById(id);
        if (!optionalNews.isPresent()) return NOT_FOUND;
        return ResponseEntity.ok(new ApiResponse(null, 200, optionalNews.get()));
    }

    @Override
    public HttpEntity<ApiResponse> deleteNews(User user, long id) {
        try {
            newsRepository.deleteById(id);
            return SUCCESS;
        } catch (Exception ex) {
            return ResponseEntity.status(406).body(new ApiResponse(ex.getMessage(), 406, null));
        }
    }

    @Override
    public HttpEntity<ApiResponse> addNews(User user, NewsDto dto) {
        try {
            Optional<StudyCentre> optionalStudyCentre = studyCentreRepository.findById(dto.getStudyCentreId());
            if (!optionalStudyCentre.isPresent()) return NOT_FOUND;
            News news = new News();
            news.setTitle(dto.getTitle());
            news.setDescription(dto.getDescription());
            news.setDate(new Date(Date.parse(dto.getDate())));
            news.setActive(dto.isActive());
            news.setStudyCentre(optionalStudyCentre.get());
            newsRepository.save(news);
            return SUCCESS;
        } catch (Exception ex) {
            return ResponseEntity.status(406).body(new ApiResponse(ex.getMessage(), 406, null));
        }
    }

    @Override
    public HttpEntity<ApiResponse> editNews(User user, NewsDto dto, long id) {
        return ResponseEntity.status(400).body(new ApiResponse("On progress", 400, null));
    }

    @Override
    public HttpEntity<ApiResponse> activate(User user, long id, boolean isActive) {
        Optional<News> optionalNews = newsRepository.findById(id);
        if (!optionalNews.isPresent()) return NOT_FOUND;
        News news = optionalNews.get();
        news.setActive(isActive);
        newsRepository.save(news);
        return SUCCESS;
    }


}
