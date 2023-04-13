package uz.o_rustamov.readium.payment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.o_rustamov.readium.payment.model.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Page<Payment> findAllByStudent_Id(Long student_id, Pageable pageable);

}
