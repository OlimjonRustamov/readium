package uz.o_rustamov.readium.news_events;

import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.readium.annotation.CurrentUser;
import uz.o_rustamov.readium.news_events.dto.NewsDto;
import uz.o_rustamov.readium.news_events.service.NewsServiceImpl;
import uz.o_rustamov.readium.user.model.User;

import javax.validation.Valid;

@Transactional
@RestController
@RequestMapping("/api/news")
public class NewsController {

    NewsServiceImpl newsService;

    public NewsController(NewsServiceImpl newsService) {
        this.newsService = newsService;
    }

    @GetMapping()
    public HttpEntity<?> getNews(@CurrentUser User user) {
        return newsService.getStudyCentreNews(user);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getSingleNews(@CurrentUser User user, @PathVariable long id) {
        return newsService.getSingleNews(user, id);
    }

    @PreAuthorize("hasAuthority('DELETE_NEWS')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteNews(@CurrentUser User user, @PathVariable long id) {
        return newsService.deleteNews(user, id);
    }

    @PreAuthorize("hasAuthority('ADD_NEWS')")
    @PostMapping()
    public HttpEntity<?> addNews(@CurrentUser User user, @Valid @RequestBody NewsDto dto) {
        return newsService.addNews(user, dto);
    }

    //there should have been edit function



    @PreAuthorize("hasAuthority('ACTIVATE_NEWS')")
    @PostMapping(("/activate-news"))
    public HttpEntity<?> addNews(@CurrentUser User user, @RequestParam long id, @RequestParam("is_active") boolean isActive) {
        return newsService.activate(user, id, isActive);
    }


}
