package uz.o_rustamov.readium.payment;

import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.o_rustamov.readium.annotation.CurrentUser;
import uz.o_rustamov.readium.payment.dto.PaymentDto;
import uz.o_rustamov.readium.payment.service.PaymentService;
import uz.o_rustamov.readium.user.model.User;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public HttpEntity<?> getMyPayments(@CurrentUser User user) {
        return paymentService.getMyPayments(user);
    }

    @PreAuthorize("hasAuthority('VIEW_PAYMENTS')")
    @GetMapping("/{id}")
    public HttpEntity<?> getSinglePayment(@PathVariable int id) {
        return paymentService.getSinglePayment(id);
    }

    @PreAuthorize("hasAuthority('VIEW_PAYMENTS')")
    @GetMapping("/user/{id}")
    public HttpEntity<?> getUserPayments(@PathVariable int id) {
        return paymentService.getUserPayments(id);
    }

    @PreAuthorize("hasAuthority('DELETE_PAYMENT')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deletePayment(@PathVariable int id) {
        return paymentService.deletePayment(id);
    }

    @PreAuthorize("hasAuthority('CREATE_PAYMENT')")
    @PostMapping
    public HttpEntity<?> createPayment(@Valid @RequestBody PaymentDto dto) {
        return paymentService.createPayment(dto);
    }
}
