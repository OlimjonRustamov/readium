package uz.o_rustamov.readium.payment.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.o_rustamov.readium.ApiResponse;
import uz.o_rustamov.readium.group.model.Group;
import uz.o_rustamov.readium.group.repository.GroupRepository;
import uz.o_rustamov.readium.payment.dto.PaymentDto;
import uz.o_rustamov.readium.payment.model.Payment;
import uz.o_rustamov.readium.payment.repository.PaymentRepository;
import uz.o_rustamov.readium.user.model.User;
import uz.o_rustamov.readium.user.repository.UserRepository;

import java.util.Optional;

import static uz.o_rustamov.readium.Constants.NOT_FOUND;
import static uz.o_rustamov.readium.Constants.SUCCESS;

@Service
public class PaymentServiceImpl implements PaymentService {

    PaymentRepository paymentRepository;
    UserRepository userRepository;

    GroupRepository groupRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, UserRepository userRepository, GroupRepository groupRepository) {
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public HttpEntity<ApiResponse> getMyPayments(User user) {
        return ResponseEntity.ok(new ApiResponse(null, 200, paymentRepository.findAllByStudent_Id(user.getId())));
    }

    @Override
    public HttpEntity<ApiResponse> getUserPayments(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return NOT_FOUND;
        User user = optionalUser.get();
        return ResponseEntity.ok(new ApiResponse(null, 200, paymentRepository.findAllByStudent_Id(user.getId())));
    }

    @Override
    public HttpEntity<ApiResponse> getSinglePayment(long id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (!optionalPayment.isPresent()) return NOT_FOUND;
        Payment payment = optionalPayment.get();
        return ResponseEntity.ok(new ApiResponse(null, 200, payment));
    }

    @Override
    public HttpEntity<ApiResponse> createPayment(PaymentDto dto) {
        Optional<User> optionalUser = userRepository.findById(dto.getStudentId());
        if (!optionalUser.isPresent()) return NOT_FOUND;
        User student = optionalUser.get();
        Optional<Group> optionalGroup = groupRepository.findById(dto.getGroupId());
        if (!optionalGroup.isPresent()) return NOT_FOUND;
        Group group = optionalGroup.get();
        Payment payment = new Payment();
        payment.setDate(System.currentTimeMillis());
        payment.setStudent(student);
        payment.setGroup(group);
        payment.setSum(dto.getSum());
        payment.setMonth(dto.getMonth());
        paymentRepository.save(payment);
        return SUCCESS;
    }

    @Override
    public HttpEntity<ApiResponse> deletePayment(long id) {
        try {
            paymentRepository.deleteById(id);
            return SUCCESS;
        } catch (Exception exception) {
            return ResponseEntity.status(400).body(new ApiResponse(exception.getMessage(), 400, null));
        }
    }
}
