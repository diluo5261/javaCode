package com.dilo.vodservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface VideoService {
    String uploadVideoVOD(MultipartFile file);
}
