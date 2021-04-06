package br.com.edu.zup.ecommerce.images.storage;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class FakeStorageUploader implements StorageImages {

    @Override
    public List<String> uploadImages(List<MultipartFile> images) {
          return images.stream().map(img -> UriComponentsBuilder.fromPath("products/pictures/{UUID}/{img}")
          .host("www.hostimages.com").scheme("https").buildAndExpand(UUID.randomUUID(),img.getOriginalFilename()).toUriString()).collect(Collectors.toList());

    }
}
