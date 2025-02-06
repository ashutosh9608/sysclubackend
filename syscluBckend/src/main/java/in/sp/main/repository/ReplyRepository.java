package in.sp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    // Custom queries can be added here if needed
}
