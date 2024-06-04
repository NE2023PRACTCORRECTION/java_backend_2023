package pract.oop_java.pms.v1.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import pract.oop_java.pms.v1.enums.EFileStatus;
import pract.oop_java.pms.v1.exceptions.InvalidFileException;
import pract.oop_java.pms.v1.fileHandling.File;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface FileService {

    List<File> getAll();

    Page<File> getAll(Pageable pageable);

    File getById(UUID id);

    File create(MultipartFile document, String directory);

    boolean delete(UUID id);

    Page<File> getAllByStatus(Pageable pageable, EFileStatus status);

    File uploadFile(MultipartFile file, String directory, UUID appointeeID) throws InvalidFileException, IOException;

    String getFileExtension(String fileName);

    String handleFileName(String fileName, UUID id) throws InvalidFileException;

    boolean isValidExtension(String fileName) throws InvalidFileException;
}
