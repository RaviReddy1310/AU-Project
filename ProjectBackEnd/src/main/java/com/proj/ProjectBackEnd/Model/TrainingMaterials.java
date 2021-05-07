package com.proj.ProjectBackEnd.Model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trainingmaterials")
public class TrainingMaterials {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "courseid")
	private int courseid;
	
	@Column(name = "filedata")
	private byte[] filedata;
	
	@Column(name = "filename")
	private String filename;
	
	@Column(name = "filetype")
	private String filetype;
	
	@Column(name = "fileuploadtime")
	private Timestamp fileuploadtime;
	
	public TrainingMaterials() {
		
	}

	public TrainingMaterials(int id, int courseid, byte[] filedata, String filename, String filetype, Timestamp fileuploadtime) {
		super();
		this.id = id;
		this.courseid = courseid;
		this.filedata = filedata;
		this.filename = filename;
		this.filetype = filetype;
		this.fileuploadtime = fileuploadtime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}

	public byte[] getFiledata() {
		return filedata;
	}
	public void setFiledata(byte[] filedata) {
		this.filedata = filedata;
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public Timestamp getTimestamp() {
		return fileuploadtime;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.fileuploadtime = timestamp;
	}

}
