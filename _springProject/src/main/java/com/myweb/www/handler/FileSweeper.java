package com.myweb.www.handler;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.jni.Local;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myweb.www.domain.FileVO;
import com.myweb.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component // 빈 스캔
public class FileSweeper {
	private final String BASE_PATH = "D:\\_myweb\\_java\\fileupload\\";

	private FileDAO fdao;

	public FileSweeper(FileDAO fdao) {
		this.fdao = fdao;
	}

	// 초 분 시 일 월 요일 년도(생략가능)
	// 0초 0분 21시(일,월,요일 상관x->* , 년도 생략)
	@Scheduled(cron = "0 0 21  * * *")
	public void fileSweeper() {
		log.info(">>>> FileSweeper Running Start : { } > " + LocalDateTime.now());

		// DB에 등록된 모든 파일 목록 가져오기
		List<FileVO> dbList = fdao.selectListAllFiles();

		// 저장소를 검색할 때 필요한 파일 경로 리스트(실제 존재해야 될 리스트)
		List<String> currFiles = new ArrayList<String>();

		for (FileVO fvo : dbList) {
			String filePath = fvo.getSaveDir() + "\\" + fvo.getUuid();
			String fileName = fvo.getFileName();
			currFiles.add(BASE_PATH + filePath + "_" + fileName);

			// 이미지 파일이면 썸네일도 경로 추가
			if (fvo.getFileType() > 0) { // 이미지 파일이라면
				currFiles.add(BASE_PATH + filePath + "_th_" + fileName);
			}
		}

		// 날짜를 반영한 폴더 구조 경로 만들기
		LocalDate now = LocalDate.now();
		String today = now.toString();
		today = today.replace("-", File.separator);

		// 경로를 기반으로 저장되어있는 파일을 검색
		File dir = Paths.get(BASE_PATH + today).toFile(); // toFile : 파일 한개 가져오는 거
		File[] allFileObjects = dir.listFiles(); // listFiles : 전체 파일을 다 가져오는 거

		// 실제 저잗되어 있는 파일과 DB에 존재하는 파일을 비교하여 없는 삭제 진행
		for (File file : allFileObjects) {
			String storedFileName = file.toPath().toString();
			if (!currFiles.contains(storedFileName)) {// DB에 storedFileName이 없다면
				file.delete(); // 파일 삭제
				log.info(">>>>> delete File > {} >> " + storedFileName);
			}
		}
		log.info(">>>> FileSweeper Running finish : { } > " + LocalDateTime.now());
	}

}
