package egovframework.let.utl.fcc.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import egovframework.com.cmm.service.FileVO;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;

//@Component("fileMngUtil")
public class FileMngUtil {
	
	public static final int BUFF_SIZE = 2048;
	
	@Resource(name = "propertyService")
	protected EgovPropertyService propertyService;
	
	@Resource(name = "egovFileIdGnrService")
	private EgovIdGnrService idgenService;
	
	
	public List<FileVO> parseFileInf(Map<String, MultipartFile> files, String KeyStr, int fileKeyParam, String atchFileId, String storePath) throws Exception {
		
		int fileKey = fileKeyParam;
		
		//파일저장경로
		String storePathString = "";
		//첨부파일ID
		String atchFileIdString = "";
		
		//파일 저장경로 여부
		if("".equals(storePath) || storePath == null) {
			storePathString = propertyService.getString("Globals.fileStorePath");
		} else {
			storePathString = propertyService.getString(storePath); 
		}
		
		//첨부파일ID 생성 및 업데이트 여부
		if("".equals(atchFileId) || atchFileId == null) {
			atchFileIdString = idgenService.getNextStringId();
		} else {
			atchFileIdString = atchFileId;
		}
		
		//폴더경로 설정
		File saveFolder = new File(storePathString); 
		if(!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}
		
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;
		String filePath = "";
		List<FileVO> result = new ArrayList<FileVO>();
		FileVO fvo;
		
		while(itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
			
			file = entry.getValue();
			String originFilename = file.getOriginalFilename();
			
			//----------------------------------
			// 위 파일명이 없는 경우 처리 
			// (첨부가 되지 않은 input file type)
			//----------------------------------
			if("".equals(originFilename)) {
				continue;
			}
			////--------------------------------
			
			//파일 확장자 체크
			int index = originFilename.lastIndexOf(".");
			String fileExt = originFilename.substring(index + 1);
			
			//저장파일명
			String newName = KeyStr + EgovStringUtil.getTimeStamp() + fileKey;
			
			//파일사이즈
			long size = file.getSize();
			
			//파일저장
			if(!"".equals(originFilename)) {
				filePath = storePathString + File.separator + newName;
				file.transferTo(new File(filePath));
			}
			fvo = new FileVO();
			fvo.setFileExtsn(fileExt); //파일확장자
			fvo.setFileStreCours(storePathString); //파일저장경로
			fvo.setFileMg(Long.toString(size));  //파일크기
			fvo.setOrignlFileNm(originFilename); //원파일명
			fvo.setStreFileNm(newName); //저장파일명
			fvo.setAtchFileId(atchFileIdString);  //첨부파일 아이디
			fvo.setFileSn(String.valueOf(fileKey)); //파일연번
			
			result.add(fvo);
			
			fileKey++;
		}
		
		return result;
	}
	
}
