package br.com.edu.zup.ecommerce.images;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public class ImagesRequest {

    @NotNull @Size(min = 1)
    private final List<MultipartFile> imagesList;

    public ImagesRequest(@NotNull @Size(min = 1) List<MultipartFile> imagesList) {
        this.imagesList = imagesList;
    }

    public List<MultipartFile> getImagesList() {
        return imagesList;
    }

    //1
    public List<Image> toImagesList(List<String> imagesLinks){
        return imagesLinks.stream().map(Image::new).collect(Collectors.toList());
    }

}
