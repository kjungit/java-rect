package org.example.authjpa.service;

import org.example.authjpa.exception.FileNotFoundException;
import org.example.authjpa.exception.FileStorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {
    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeFile( MultipartFile file ) {
        if (file == null || file.isEmpty()) return null;

        try {
            File dir = new File(uploadDir).getAbsoluteFile();
            if (!dir.exists()) dir.mkdirs();

            String storeFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            File dest = new File(dir, storeFileName);

            file.transferTo(dest);
            return storeFileName;
        } catch (IOException e) {
            throw new IllegalStateException("파일 저장에 실패 했습니다.", e);
        }
    }

    public Resource downloadFile( String fileName ) {
        File file = new File(new File(uploadDir).getAbsoluteFile(), fileName);

        if (!file.exists()) {
            throw new FileNotFoundException("파일을 찾을 수 없습니다.");
        }

        try {
            Resource resource = new UrlResource(file.toURI());
            if (!resource.isReadable()) {
                throw new FileStorageException("파일을 읽을 수 없습니다.", null);
            }


            return resource;
        } catch (IOException e) {
            throw new FileStorageException("파일을 불러오는 중 오류가 발생했습니다.", e);
        }
    }


    public void deleteFile(String filePath) {
        if (filePath == null || filePath.isBlank()) return;
        File file = new File(filePath);
        if (!file.exists()) return;

        file.delete();
    }

}
