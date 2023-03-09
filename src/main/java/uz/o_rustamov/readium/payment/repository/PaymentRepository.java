package uz.o_rustamov.readium.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.o_rustamov.readium.payment.model.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findAllByStudent_Id(Long student_id);

}
