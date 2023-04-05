package uz.o_rustamov.readium.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.o_rustamov.readium.ApiResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    JwtProvider jwtProvider;
    MyUserDetailsService myUserDetailsService;


    public JwtFilter(JwtProvider jwtProvider, MyUserDetailsService myUserDetailsService) {
        this.jwtProvider = jwtProvider;
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("x-access-token");
        if (token != null) {
            try {
                boolean validateToken = jwtProvider.validateToken(token);
                if (validateToken) {
                    String username = jwtProvider.getUsernameFromToken(token);
                    UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null,
                                    userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } catch (ExpiredJwtException ex) {
                performError(response, "Identifikatsiya ma'lumotlari eskirgan");
                return;
            } catch (SignatureException ex) {
                performError(response, "Identifikatsiya ma'lumotlari noto'g'ri");
                return;
            } catch (Exception e) {
                performError(response, e.getMessage());
                return;
            }
        } else if (!request.getServletPath().equals("/api/auth/login")) {
            performError(response, "Identifikatsiyadan o'tish talab etiladi");
            return;
        }
        filterChain.doFilter(request, response);
    }

    private void performError(HttpServletResponse response, String message) throws IOException {
        ApiResponse errorResponse = new ApiResponse(
                message, 401, null);

        response.setStatus(401);
        response.setContentType("application/json");
        String json = new ObjectMapper().writer().writeValueAsString(errorResponse);
        response.getWriter().write(json);

    }
}
