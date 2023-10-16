package com.ezen.myproject.repository;

import java.util.List;

import com.ezen.myproject.domain.FileVo;

public interface FileDAO {

	int insertFile(FileVo fvo);

	List<FileVo> getFileList(int bno);

	int removeFile(String uuid);





}
