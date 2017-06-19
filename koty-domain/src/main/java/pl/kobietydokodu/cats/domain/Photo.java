package pl.kobietydokodu.cats.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Represents a photo that is assigned to the cat.
 */
@Entity
@Table(name = "photos")
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "original_file_name", nullable = false)
	private String originalFileName;

	@Column(name = "file_name", nullable = false)
	private String fileName;

	@Column(name = "file_description", length = 512)
	private String fileDescription;

	@Column(name = "file_size")
	private int fileSize;

	@Column(name = "file_type")
	private String fileType;

	@OneToOne
	@JoinColumn(name = "cat_id")
	private Cat kitten;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Cat getKitten() {
		return kitten;
	}

	public void setKitten(Cat kitten) {
		this.kitten = kitten;
	}

}
