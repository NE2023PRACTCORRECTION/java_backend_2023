package pract.oop_java.pms.v1.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pract.oop_java.pms.v1.enums.EFileStatus;
import pract.oop_java.pms.v1.fileHandling.File;

import java.util.List;
import java.util.UUID;

public interface FileRepository extends JpaRepository<File, UUID> {
    Page<File> findAllByStatus(Pageable pageable, EFileStatus status);

    List<File> findByPathContainsAndNameContains(String path, String name);

}
