/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import models.User;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author awarsyle
 */
public class AccountService {

    public User login(String username, String password, String path) {
        try {
            UserDB userDB = new UserDB();
            User user = userDB.getUser(username);

            if (user.getPassword().equals(password)) {
                // successful login
                Logger.getLogger(AccountService.class.getName())
                        .log(Level.INFO, "User {0} logged in.", user.getUsername());
                
                // send email upon successful login
                //GmailService.sendMail(user.getEmail(), "Notes App Login",
                //        "Hi " + user.getFirstname() + "\nYou just logged in.", false);
                String email = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/login.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstname());
                tags.put("date", ((new java.util.Date())).toString());
                
                GmailService.sendMail(email, subject, template, tags);
                
                return user;
            }
        } catch (Exception e) {

        }

        return null;
    }
    
    public boolean forgotPassword (String email) {
        UserService us = new UserService();
        User user = us.getByEmail(email);
        String firstname = user.getFirstname();
        String lastname = user.getLastname();
        String username = user.getUsername();
        String password = user.getPassword();
        
        String template = "Hi" + firstname + " " + lastname + ",\n" + 
                "Here are your credentials to log back into Notes Keepr.\n" +
                "Username: " + username + "\n" +
                "password: " + password + "\n";
        
        try {
            GmailService.sendMail(email, "Forgot Password", template, false);
            return true;
        } catch (MessagingException | NamingException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
