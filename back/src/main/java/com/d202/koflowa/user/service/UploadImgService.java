package com.d202.koflowa.user.service;

//import com.amazonaws.services.s3.AmazonS3Client;
//import com.amazonaws.services.s3.model.CannedAccessControlList;
//import com.amazonaws.services.s3.model.PutObjectRequest;
import com.d202.koflowa.exception.ImageConvertException;
import com.d202.koflowa.exception.UserNotFoundException;
import com.d202.koflowa.user.domain.User;
import com.d202.koflowa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class UploadImgService {
//    private final AmazonS3Client amazonS3Client;
    private final UserRepository userRepository;

//    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String upload(MultipartFile multipartFile, String dirname, long id) throws IOException {
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new ImageConvertException("이미지를 multipart -> file로 변환하는 것을 실패했습니다."));
        String uploadURL = upload(uploadFile, dirname);
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            user.get().putProfile(uploadURL);
            userRepository.save(user.get());
            return userRepository.findById(id).get().getProfile();
        }else{
            throw new UserNotFoundException("유저를 찾을 수 없습니다.");
        }
    }

    private String makeFileName(String filename){
        String extension = filename.substring(filename.lastIndexOf("."), filename.length());
        UUID uuid = UUID.randomUUID();
        return uuid.toString() + extension;
    }

    private String upload(File uploadFile, String dirname){
        String fileName = dirname + "/" + makeFileName(uploadFile.getName());
        String uploadImgUrl = saveS3(uploadFile, fileName);
        removeNewFile(uploadFile);
        return uploadImgUrl;
    }

    private String saveS3(File uploadFile, String fileName){
//        amazonS3Client.putObject(new PutObjectRequest(bucket,fileName, uploadFile)
//                .withCannedAcl(CannedAccessControlList.PublicRead));
//        return amazonS3Client.getUrl(bucket, fileName).toString();
        return "";
    }

    private void removeNewFile(File targetFile){
        if (targetFile.delete()){
            log.info("파일 삭제 완료");
        }else{
            log.info("파일 삭제 실패");
        }
    }

    private Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        if (convertFile.createNewFile()){
            try(FileOutputStream fos = new FileOutputStream(convertFile)){
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }
}
