package com.myweb.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.FileVO;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@ToString
@Getter
@Component
@Slf4j
public class FileHandler {
	private final String UP_DIR = "D:\\_myweb\\_java\\fileupload";

	public List<FileVO> uploadFiles(MultipartFile[] files) {
		List<FileVO> flist = new ArrayList<FileVO>();
		/* 파일 경로, fvo set, 파일 저장... */

		// 1. 파일 경로(날짜를 폴더로 생성하여 일자별로 업로드 파일을 관리)
		LocalDate date = LocalDate.now(); // 오늘 날짜(LocalDate객체)
		String today = date.toString(); // LocalDate객체를 String으로 변환(2023-10-24)
		today = today.replace("-", File.separator); // 2023\10\24(윈도우), 2023/10/24(리눅스,맥)

		File folders = new File(UP_DIR, today); // D:\\_myweb\\_java\\fileupload 하위에 오늘 날짜 경로

		// 폴더 생성(mkdir은 폴더 1개, mkdirs는 여러개)
		if (!folders.exists()) { // folders가 없다면
			folders.mkdirs();
		}

		// 2. files 객체에 대한 설정(fvo set)
		for (MultipartFile file : files) {// 들어온 첨부파일 1개씩 for문 처리
			FileVO fvo = new FileVO();
			fvo.setSaveDir(today); // 2023\10\24...?
			fvo.setFileSize(file.getSize());

			String originalFileName = file.getOriginalFilename(); // 실제 파일이름(ex:이벤트.jpg)
			// originalFileName에 가끔 앞의 경로가 포함되어 있는 경우가 있어서 잘라줌
			String fileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator) + 1);
			fvo.setFileName(fileName);
			UUID uuid = UUID.randomUUID();
			fvo.setUuid(uuid.toString());// UUID 객체이기 때문에 toString()해줘야 함

			// 3. 하단부터 디스크에 저장할 파일 객체 생성(파일 저장)
			// 파일 이름 => uuid_fileName / 썸네일 파일 => uuid_th_fileName
			String fullFileName = uuid.toString() + "_" + fileName;

			File storeFile = new File(folders, fullFileName); // 파일 생성
			// file 객체가 저장이 되려면 첫 경로부터 다 설정이 되어 있어야 함.
			// D:\\_myweb\\_java\\fileupload\\2023\\10\\24\\uuid_fileName.jpg
			// folders => D:\\_myweb\\_java\\fileupload2023\\10\\24\\
			// fullFileName => uuid_fileName.jpg

			try {
				file.transferTo(storeFile); // 저장
				// 썸네일 생성 => 이미지 파일만 썸네일 생성
				// 이미지 파일인지 확인
				if (isImageFile(storeFile)) {
					fvo.setFileType(1); // 이미지 파일만 타입에 1 설정
					// 썸네일 파일 => uuid_th_fileName
					File thumbNail = new File(folders, uuid.toString() + "_th_" + fileName);
					Thumbnails.of(storeFile).size(75, 75).toFile(thumbNail);
				}
			} catch (Exception e) {
				log.debug(">>> 파일 생성 오류~!!");
				e.printStackTrace();
			}
			// flist에 fvo 추가
			flist.add(fvo);
		}

		return flist;
	}

	// 이미지 파일인지 확인하는 메서드
	private boolean isImageFile(File storeFile) throws IOException {
		// detect => tika는 파일 형식을 확인할 때 Detector라는 인터페이스를 구현한 객체의 detect 메서드를 사용
		String mimeType = new Tika().detect(storeFile); // image/jpg
		return mimeType.startsWith("image") ? true : false;

	}
}
