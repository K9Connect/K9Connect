package net.k9connect.k9connect.controllers;

import net.k9connect.k9connect.models.Message;
import net.k9connect.k9connect.models.User;
import net.k9connect.k9connect.repositories.MessagesRepository;
import net.k9connect.k9connect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private MessagesRepository messageDao;

    @Autowired
    private UserRepository userDao;

    @GetMapping("/message/{id}")
    public String createMessage(Model model, @PathVariable long id) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());
        model.addAttribute("loggedinuser", user);

        model.addAttribute("message", new Message());

        List<Message> listOfMessages = user.getMessage_received();
        List<Message> listOfMessagesSent = user.getMessage_sent();

        for (Message message : listOfMessagesSent) {
            listOfMessages.add(message);
        }

        Collections.sort(listOfMessages);

        User otherUser = userDao.getById(id);

        model.addAttribute("otheruser", otherUser);
        model.addAttribute("messages", listOfMessages);

        return "messages/message";

    }

    @PostMapping("/message/{id}")
    public String sendMessage(Model model, @PathVariable long id, @ModelAttribute Message message) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());
        User receivingUser = userDao.getById(id);

        Message newMessage = new Message();
        newMessage.setSending_user(loggedInUser);
        newMessage.setReceiving_user(receivingUser);
        newMessage.setContent(message.getContent());
        messageDao.save(newMessage);

        return "redirect:/message/" + id;


    }

    @GetMapping("/messages")
    public String showMessages(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        List<Message> messageHistory = new ArrayList<>();
        List<User> listOfUsers = new ArrayList<>();

        messageHistory.addAll(user.getMessage_received());
        messageHistory.addAll(user.getMessage_sent());


        for (int i = 0; i < messageHistory.size(); i++) {
            if (!listOfUsers.contains(messageHistory.get(i).receiving_user) && user.getId() != messageHistory.get(i).receiving_user.getId()) {
                listOfUsers.add(messageHistory.get(i).receiving_user);
            } else if (!listOfUsers.contains(messageHistory.get(i).sending_user) && user.getId() != messageHistory.get(i).sending_user.getId()) {
                listOfUsers.add(messageHistory.get(i).sending_user);
            }
        }

        model.addAttribute("users", listOfUsers);

        return "messages/index";

    }
}
