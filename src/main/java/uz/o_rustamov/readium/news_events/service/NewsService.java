package uz.o_rustamov.readium.news_events.service;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.news_events.dto.NewsDto;
import uz.o_rustamov.readium.user.model.User;

public interface NewsService {

    HttpEntity<ApiResponse> getStudyCentreNews(User user);

    HttpEntity<ApiResponse> getSingleNews(User user, long id);

    HttpEntity<ApiResponse> deleteNews(User user, long id);

    HttpEntity<ApiResponse> addNews(User user, NewsDto dto);

    HttpEntity<ApiResponse> editNews(User user, NewsDto dto, long id);

    HttpEntity<ApiResponse> activate(User user, long id, boolean isActive);//true -> make it active; for false vice versa
}
