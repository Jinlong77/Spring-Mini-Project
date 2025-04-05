package org.kshrd.gamifiedhabittracker.controller;

import lombok.RequiredArgsConstructor;
import org.kshrd.gamifiedhabittracker.model.FileMetadata;
import org.kshrd.gamifiedhabittracker.model.dto.response.Response;
import org.kshrd.gamifiedhabittracker.service.FileService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static org.kshrd.gamifiedhabittracker.constant.Constant.FILE_API;
import static org.kshrd.gamifiedhabittracker.utils.RequestUtils.getResponse;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(FILE_API)
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping(value = "/upload-file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response<FileMetadata>> uploadFile(@RequestParam MultipartFile file){
        FileMetadata fileMetadata = fileService.uploadFile(file);
        return ResponseEntity.ok(getResponse("", OK, fileMetadata));
    }

    @GetMapping("/preview-file/{file-name}")
    public ResponseEntity<?> getFileByFileName(@PathVariable("file-name") String fileName) throws IOException {
        InputStream inputStream = fileService.getFileByFileName(fileName);
        return ResponseEntity.status(OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(inputStream.readAllBytes());
    }
}
