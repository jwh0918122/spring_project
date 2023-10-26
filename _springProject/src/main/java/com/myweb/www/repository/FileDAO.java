package com.myweb.www.repository;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.FileVO;

public interface FileDAO {

	int insertFile(FileVO fvo);

	List<FileVO> getFileList(long bno);

	int fileCount(long bno);

	int fileDelete(String uuid);

	void deleteFileAll(long bno);

	List<FileVO> selectListAllFiles();


}
