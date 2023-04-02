package Main.Util;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MailUtil {
	public String[] parseStringEmailToArray(String emailString) {
		String[] arrEmail = null;
		if(emailString.length() > 0) {
			emailString = emailString.replaceAll(" ", "");
			arrEmail = emailString.split(",");
		}
		return arrEmail;
	}
	public File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException, Exception {
		File convertFile = new File(System.getProperty("java.io.tmpdir") + "/" + multipartFile.getOriginalFilename());
		multipartFile.transferTo(convertFile);
		return convertFile;
	}
}
