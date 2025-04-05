package org.kshrd.gamifiedhabittracker.service.impl;

import io.minio.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.kshrd.gamifiedhabittracker.model.FileMetadata;
import org.kshrd.gamifiedhabittracker.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.InputStream;
import java.util.UUID;

import static org.kshrd.gamifiedhabittracker.constant.Constant.FILE_API;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Value("${minio.bucket.name}")
    private String bucketName;

    private final MinioClient minioClient;

    @SneakyThrows
    @Override
    public FileMetadata uploadFile(MultipartFile file) {

        boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());

        if (!bucketExists)
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());

        String fileName = file.getOriginalFilename();

        fileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(fileName);

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .contentType(file.getContentType())
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .build());

        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(FILE_API +  "/preview-file/" + fileName)
                .toUriString();

        return FileMetadata.builder()
                .fileName(fileName)
                .fileUrl(fileUrl)
                .fileType(file.getContentType())
                .fileSize(file.getSize())
                .build();
    }

    @SneakyThrows
    @Override
    public InputStream getFileByFileName(String fileName) {
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(fileName)
                        .build()
        );
    }
}
