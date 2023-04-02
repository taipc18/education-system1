package Main.service;

import javax.mail.MessagingException;

import Main.entity.MailInfor;



public interface MailService {
	void send(MailInfor mailInfor) throws MessagingException;
	void send(String to, String subject, String body) throws MessagingException;
	void queue(MailInfor mailInfor);
	void queue(String to, String subject, String body);
}
