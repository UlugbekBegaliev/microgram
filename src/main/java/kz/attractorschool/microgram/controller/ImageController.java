package kz.attractorschool.microgram.controller;

import kz.attractorschool.microgram.dto.ImageDTO;
import kz.attractorschool.microgram.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
@AllArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping
    public ImageDTO addPublicationImage(@RequestParam("file") MultipartFile file) {
        return imageService.addImage(file);
    }
}
