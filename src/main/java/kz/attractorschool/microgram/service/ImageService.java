package kz.attractorschool.microgram.service;

import kz.attractorschool.microgram.repository.ImageRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ImageService {
    private ImageRepository imageRepository;
}
