package org.kshrd.gamifiedhabittracker.service;

import org.kshrd.gamifiedhabittracker.model.FileMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {
    FileMetadata uploadFile(MultipartFile file);
    InputStream getFileByFileName(String fileName);
}
