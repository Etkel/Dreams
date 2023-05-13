package com.shop.dream.configs;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.io.ByteStreams;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class GoogleCloudConfig {
    private static Storage storage;

    @Bean
    public Storage storage() throws IOException {
        InputStream inputStream = new ClassPathResource("/dream-shop-380613-37d315d7f2c8.json").getInputStream();
        GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream);
        storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        return storage;
    }

    public static void uploadFile(MultipartFile file, String filename) throws IOException {
        InputStream inputStream = file.getInputStream();
        BlobId blobId = BlobId.of("dream-shop-380613", filename);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
        byte[] bytes = ByteStreams.toByteArray(inputStream);
        storage.create(blobInfo, bytes);
    }
}
