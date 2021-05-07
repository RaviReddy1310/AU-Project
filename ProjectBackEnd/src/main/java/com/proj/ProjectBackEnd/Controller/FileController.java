package com.proj.ProjectBackEnd.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.rowset.serial.SerialException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.proj.ProjectBackEnd.Model.TrainingMaterials;
import com.proj.ProjectBackEnd.Service.CourseService;
import com.proj.ProjectBackEnd.Service.FileService;

@RestController
@RequestMapping("app/file")
@CrossOrigin
public class FileController {
	
	@Autowired
	public FileService fileService;
	
	@Autowired
	public CourseService courseService;
	
	Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@PostMapping("/add-file")
	@Transactional
	public String addFile(@RequestParam("id") int cid,
		@RequestParam("name") String filename,@RequestParam("data") MultipartFile data) throws IOException, SerialException, SQLException {
		
		logger.info("Request to upload a file is made and is done");
		String filetype =  data.getContentType();
		byte[] fileBytes = data.getBytes();
		
		return fileService.addFile(cid,fileBytes,filename,filetype);
	}
	
	@GetMapping("/all-versions/{cid}")
	public List<TrainingMaterials> allVersions(@PathVariable("cid") int cid) {
		logger.info("Request to get all versions of a course ID:"+cid+" is made");
		List<TrainingMaterials> res = fileService.allVersions(cid);
		if(res.isEmpty()) {
			logger.info("No versions are available");
			return null;
		}
		logger.info("AllVersions are retrieved");
		return res;
	}
	
	@GetMapping("/get-material-cid/{cid}")
	public TrainingMaterials getMaterialByCId(@PathVariable("cid") int cid) {
		logger.info("Request to get material of course ID:"+cid+" is made");
		Timestamp time = courseService.getTimestamp(cid);
		if(time == null) {
			logger.info("No material is available");
			return null;
		}
		logger.info("Requested material is retrieved");
		return fileService.getMaterialByTS(cid, time);
	}
	
	@GetMapping("/delete-version/{vid}")
	public String deleteVersion(@PathVariable("vid") int id) {
		logger.info("Request to delete a version ID:"+id+" is made and is done");
		return fileService.deleteVersion(id);
	}

}
