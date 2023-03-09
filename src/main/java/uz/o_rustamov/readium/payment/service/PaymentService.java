package uz.o_rustamov.readium.payment.service;

import org.springframework.http.HttpEntity;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.payment.dto.PaymentDto;
import uz.o_rustamov.readium.user.model.User;

public interface PaymentService {

    HttpEntity<ApiResponse> getMyPayments(User user);
    HttpEntity<ApiResponse> getUserPayments(long id);

    HttpEntity<ApiResponse> getSinglePayment(long id);
    HttpEntity<ApiResponse> createPayment(PaymentDto dto);

    HttpEntity<ApiResponse> deletePayment(long id);
}
