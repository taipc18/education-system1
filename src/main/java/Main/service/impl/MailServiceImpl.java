package Main.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;




import Main.entity.MailInfor;
import Main.service.MailService;

@Service
public class MailServiceImpl implements MailService{
	private List<MailInfor> listMail = new ArrayList<>();
	@Autowired
	JavaMailSender javaMailSender;
	@Override
	public void send(MailInfor mailInfor) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
		helper.setFrom(mailInfor.getFrom());
		helper.setTo(mailInfor.getTo());
		helper.setSubject(mailInfor.getSubject());
		helper.setText(mailInfor.getBody(), true);
		helper.setReplyTo(mailInfor.getFrom());
		String[] cc = mailInfor.getCc();
		if(cc != null && cc.length > 0) {
			helper.setCc(cc);
		}
		String[] bcc = mailInfor.getBcc();
		if(bcc != null && bcc.length > 0) {
			helper.setBcc(bcc);
		}
		List<File> files = new ArrayList<>();
		if(files.size() > 0) {
			for(File file : files) {
				helper.addAttachment(file.getName(), file);
			}
		}
		javaMailSender.send(message);
	}

	@Override
	public void send(String to, String subject, String body) throws MessagingException {
		this.send(new MailInfor(to, subject, body));
	}

	@Override
	public void queue(MailInfor mailInfor) {
		listMail.add(mailInfor);
		
	}

	@Override
	public void queue(String to, String subject, String body) {
		queue(new MailInfor(to, subject, body));
		
	}
	
	@Scheduled(fixedDelay = 5000)
	public void run() {
		while(!listMail.isEmpty()) {
			MailInfor mailInfo = listMail.remove(0);
			try {
				this.send(mailInfo);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}

