package com.dailycodework.buynowdotcom.controller;

import com.dailycodework.buynowdotcom.dtos.ImageDto;
import com.dailycodework.buynowdotcom.model.Category;
import com.dailycodework.buynowdotcom.model.Image;
import com.dailycodework.buynowdotcom.response.ApiResponse;
import com.dailycodework.buynowdotcom.service.image.IImageService;
import com.dailycodework.buynowdotcom.service.image.ImageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/images")
public class ImageController {
    private final IImageService iImageService;
    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> uploadImage(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("productId") Long productId){

        try {
            List<ImageDto> imageDto = iImageService.saveImages(productId, files);
            return ResponseEntity.ok(new ApiResponse("Images uploaded successfully", imageDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Upload error!", e.getMessage()));
        }
    }

    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) throws Exception{
        Image image = imageService.getImageById(imageId);
        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1, (int) image.getImage().length()));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                        + image.getFileName() + "\"").body(resource);
    }

    @PutMapping("/image/{imageId}/update")
    public ResponseEntity<ApiResponse> updateImage(@PathVariable Long imageId, @RequestBody MultipartFile file){
        try {
            imageService.updateImage(file, imageId);
            return ResponseEntity.ok(new ApiResponse("Image update successfully!", null));
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }

    }

    @DeleteMapping("/image/{imageId}/delete")
    public ResponseEntity<ApiResponse> deleteImage(@PathVariable Long imageId){
        try {
            imageService.deleteImageById(imageId);
            return ResponseEntity.ok(new ApiResponse("Delete Image Success!", null));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

}
