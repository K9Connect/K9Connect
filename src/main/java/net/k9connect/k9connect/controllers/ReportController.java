package net.k9connect.k9connect.controllers;

import net.k9connect.k9connect.models.User;
import net.k9connect.k9connect.models.UserReport;
import net.k9connect.k9connect.repositories.ReportsRepository;
import net.k9connect.k9connect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ReportController {

    @Autowired
    private UserRepository userDao;

    @Autowired
    private ReportsRepository reportsDao;


    @GetMapping("/report/{id}")
    public String displayReport(@PathVariable long id, Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());

        model.addAttribute("loggedinuser", user);
        User reportedUser = userDao.getById(id);
        model.addAttribute("reporteduser", reportedUser);
        model.addAttribute("report", new UserReport());


        return "/users/reports/report-user";

    }

    @PostMapping("report/{id}")
    public String submitReport(@PathVariable long id, Model model, @ModelAttribute UserReport report) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByUsername(loggedInUser.getUsername());
        User reportedUser = userDao.getById(id);

        UserReport newReport = new UserReport();
        newReport.setReportText(report.getReportText());
        newReport.setReportedUser(reportedUser);
        newReport.setReportingUser(user);
        reportsDao.save(newReport);

        return "redirect:/report/" + reportedUser.getId() + "?success";


    }

}
