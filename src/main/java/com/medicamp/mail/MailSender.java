package com.medicamp.mail;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.medicamp.db.UserRepository;
import com.medicamp.model.Activiteit;
import com.medicamp.model.Kind;
import com.medicamp.model.User;

public class MailSender {

	private Properties props = new Properties();
	private Session session = Session.getDefaultInstance(props, null);
	private static final String NOREPLY = "noreply@medicamp-so.appspotmail.com";
	private static final String INFO = "Medicamp Info";

	public MailSender() {

	}

	private void sendMail(List<User> recipients, String subject, String message) {
		try {
			Address to[] = new Address[recipients.size()];

			for (int i = 0; i < recipients.size(); i++) {
				User user = recipients.get(i);
				to[i] = new InternetAddress(user.getLogin(), user.getVoornaam() + " " + user.getNaam());
			}

			Message msg = new MimeMessage(this.session);
			msg.setFrom(new InternetAddress(NOREPLY, INFO));
			msg.addRecipients(Message.RecipientType.TO, to);
			msg.setSubject(subject);
			msg.setText(message);
			Transport.send(msg);
		} catch (AddressException e) {
			// TODO: properly catch this exception
		} catch (MessagingException e) {
			// TODO: properly catch this exception
		} catch (UnsupportedEncodingException e) {
			// TODO: properly catch this exception
		}
	}

	public void sendAccountConfirmationMail(User to) {
		List<User> recipients = new ArrayList<>();
		recipients.add(to);
		String subject = "Medicamp account activatie";
		String text = "Beste " + to.getVoornaam() + "\n\n\n"
				+ "U heeft zich zojuist succesvol gerigstreerd voor de services van Medicamp!\n\n"
				+ "Klik op onderstaande link, of kopieer hem naar uw browser, om uw account te activeren.\n"
				+ "<a href=\"\" >Link</a>\n\n\n" + "Groetjes,\n\n" + "Het Medicamp-team";
		sendMail(recipients, subject, text);
	}

	public void sendActiviteitWarningMail(List<User> recipients, Activiteit act, String groepsnaam) {
		String subject = "Medicamp - Nieuwe activiteit gepland!";
		String text = "Beste Medicamp-lid\n\n\n" + groepsnaam + "Heeft zojuist een nieuwe activiteit geregistreerd van "
				+ act.getBegindatum() + " tot " + act.getEinddatum() + ".\n\n"
				+ "Controleer voor deze data de medische gegevens van je kind, zo dat de leiding zeker over de juiste informatie beschikt.\n\n\n"
				+ "Groetjes,\n\nHet Medicamp-team";
		sendMail(recipients, subject, text);
	}

	public void sendKindIngeschrevenWarningMail(List<User> recipients, String tak, Kind kind) {
		String subject = "Medicamp - Kind ingeschreven!";
		String text = "Beste groepsleiding\n\n\n"
				+ "Er werd zojuist een nieuw kind geregistreerd voor jouw groep bij de " + tak + ".\n\n"
				+ "controleer via <a href=\"\">deze link</a> of " + kind.getVoornaam() + " " + kind.getNaam()
				+ " effectief een lid van jouw groep is.\n\n\n" + "Groetjes,\n\nHet Medicamp-team";
		sendMail(recipients, subject, text);
	}

	public void sendLeidingInviteMail(List<User> recipients, String tak, String groepsnaam, User hoofdleider) {
		String subject = groepsnaam + " - leidingsploeg uitnodiging";
		String text = "Beste \n\n\n" + hoofdleider.getVoornaam() + " " + hoofdleider.getNaam()
				+ " nodigt je uit om leiding te worden van " + tak + " bij " + groepsnaam + "!\n\n"
				+ "Klik <a href=\"\">hier</a> om de uitnodiging te aanvaarden.\n\n"
				+ "Mail niet voor u bestemd? Geen probleem, u hoeft verder niets te doen en kan deze mail gewoon negeren.\n\n\n"
				+ "Groetjes,\n\nHet Medicamp-team";
		sendMail(recipients, subject, text);
	}

}
