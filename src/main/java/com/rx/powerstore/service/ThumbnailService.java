package com.rx.powerstore.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rx.powerstore.entity.Thumbnail;

@Service
public interface ThumbnailService {
	public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException;
	public void copyToDisk(MultipartFile multipart);
	public String getFilePath();
	public void save(Thumbnail thumbnail);
}
