package in.sp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entity.Complaint;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    // Custom queries can be added here if needed
}
