package com.ezen.myproject.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.myproject.domain.FileVo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@AllArgsConstructor
@Component // 사용자가 만든 클래스
public class FileHandler {

	// 내 파일 기본 경로
	private final String UP_DIR = "D:\\_myweb\\_java\\fileupload";

	public List<FileVo> uploadFile(MultipartFile[] files) {
		// 멀티파트 파일 객체를 받아서 FileVo 형태로 저장 한 후
		// 오늘날짜 경로(가변형태로 생성:있으면 가져오고 없으면 생성) / 실제 파일을 해당 경로에 저장
		// FileVo를 List에 추가 => return list;

		// 오늘날짜 경로
		LocalDate date = LocalDate.now(); // 자바에서 오늘 날짜를 리턴해줌
		log.info(">>> date : " + date);
		String today = date.toString(); // 2023-10-23 String으로 변환
		// 2023\\10\\13 String 생성
		today = today.replace("-", File.separator);

		// 오늘날짜(today)의 폴더 구성
		File folders = new File(UP_DIR, today);
		if (!folders.exists()) { // folders가 없다면
			folders.mkdirs(); // 폴더 생성 명령(한개의 폴더 만드는거면 mkdir())
		}

		// 리스트 설정
		List<FileVo> flist = new ArrayList<FileVo>();
		for (MultipartFile file : files) {
			FileVo fvo = new FileVo();
			fvo.setSave_dir(today); // 공통("D:\\_myweb\\_java\\fileupload")의 뒷쪽 오늘 날짜 경로만 set
			fvo.setFile_size(file.getSize()); // 파일 사이즈는 return이 long

			// 파일 이름(OriginalFilename() 설정)
			log.info(">>>>>> getName : " + file.getName()); // 파일 객체의 종류
			log.info(">> oriName : " + file.getOriginalFilename()); // 이름
			String originalFileName = file.getOriginalFilename();
			// originalFileName이 파일 경로를 포함하고 있을 수도 있어서
			String onlyFileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator) + 1); // 실
																												// 파일명만
																												// 추출(없으면																												// 안하는거)
			log.info(">>>onlyFileName >>" + onlyFileName);
			fvo.setFile_name(onlyFileName); // 파일 이름 설정

			// UUID 생성
			UUID uuid = UUID.randomUUID();
			// uuid는 uuid의 객체이므로 .toString으로 변경 후 저장
			fvo.setUuid(uuid.toString());
			log.info(">>>>> uuid : " + uuid.toString());

			// <--- 여기까지 fvo 설정 완료 fvo setting--->

			// 디스트에 저장할 파일 객체 생성 -> 저장
			// uuid_파일네임
			String fullFileName = uuid.toString() + "_" + onlyFileName;
			File storeFile = new File(folders, fullFileName);

			// 저장 => 해당 폴더가 없으면 저장이 안되기 때문에 io Exception발생하므로 try~catch 필수
			try {
				file.transferTo(storeFile);// 원본 객체 저장을 위한 형태로 변경 후 복사
				// 파일 타입을 결정 => 이미지 파일이라면 썸네일 생성
				if (isImgeFile(storeFile)) {
					fvo.setFile_type(1);
					// uuid_th_파일네임
					File thumbNail = new File(folders, uuid.toString() + "_th_" + onlyFileName);

					Thumbnails.of(storeFile).size(75, 75).toFile(thumbNail);
				}
			} catch (Exception e) {
				log.info(">>> file 생성 오류");
				e.printStackTrace();
			}

			flist.add(fvo);

		}

		return flist;

	}

	// itka를 사용하여 파일 형식 체크 -> 이미지 파일이 맞는지 확인(맞으면1,아니면0)
	public boolean isImgeFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile); // image/jpg , image/png

		return mimeType.startsWith("image") ? true : false;
	}

}
