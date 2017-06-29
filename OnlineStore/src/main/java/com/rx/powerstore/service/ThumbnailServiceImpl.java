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
	
	static final String FILE_PATH = "C:/Users/Cristian/Documents/RXPowerstore/trunk/OnlineStore/src/main/resources/static/css/images";
	private File file;
	
	@Override
	public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException{
		
		File convFile = new File(FILE_PATH, multipart.getOriginalFilename());
		multipart.transferTo(convFile);
		return convFile;
	}
	
	/* (non-Javadoc)
	 * @see com.rx.powerstore.service.ThumbnailService#getNewThumbnail(org.springframework.web.multipart.MultipartFile)
	 * Converts multipart files to files and returns a new Thumbnail with the converted file's path.
	 */
	@Override
	public Thumbnail getNewThumbnail(MultipartFile multipart) {
		try {
			file = multipartToFile(multipart);
			file.createNewFile();
			
			Thumbnail thumbnail = new Thumbnail();
			thumbnail.setFilePath("/static/css/images/" + file.getName());
			
			return thumbnail;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Thumbnail findOne(Long id) {
		return thumbnailRepository.findOne(id);
	}
	
	@Override
	public void save(Thumbnail thumbnail) {
		thumbnailRepository.save(thumbnail);
	}
	
	@Override
	public void delete(Thumbnail thumbnail) {
		thumbnailRepository.delete(thumbnail);
	}
}
