package kz.attractorschool.microgram.service;

import kz.attractorschool.microgram.dto.ImageDTO;
import kz.attractorschool.microgram.repository.ImageRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ImageService {
    private ImageRepository imageRepository;

    public ImageDTO addImage(MultipartFile file) {
        return ImageDTO.builder().build();
    }
}
//нужно доработать логику добавления изображения