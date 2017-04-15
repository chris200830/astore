package com.rx.powerstore.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.rx.powerstore.entity.Thumbnail;
import com.rx.powerstore.repository.ThumbnailRepository;

@Component
public class ThumbnailServiceImpl implements ThumbnailService{
	@Autowired
	private ThumbnailRepository thumbnailRepository;
	
	static final String FILE_PATH = "C:/Users/Cristian/Pictures/OnlineStore_Images";
	private File file;
	
	@Override
	public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException{
		
		File convFile = new File(FILE_PATH, multipart.getOriginalFilename());
		multipart.transferTo(convFile);
		return convFile;
	}
	
	@Override
	public void copyToDisk(MultipartFile multipart) {
		try {
			file = multipartToFile(multipart);
			file.createNewFile();
			
			Thumbnail thumbnail = new Thumbnail();
			thumbnail.setFilePath(file.getAbsolutePath());
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String getFilePath() {
		return file.getAbsolutePath();
	}
	
	@Override
	public void save(Thumbnail thumbnail) {
		thumbnailRepository.save(thumbnail);
	}
}
