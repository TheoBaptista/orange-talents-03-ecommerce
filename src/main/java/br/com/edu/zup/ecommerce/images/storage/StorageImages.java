package br.com.edu.zup.ecommerce.images.storage;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface StorageImages {

    List<String> uploadImages(List<MultipartFile> images);

}
