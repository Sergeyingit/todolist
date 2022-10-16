package com.github.sergeyingit.todolist.controller;

import com.github.sergeyingit.todolist.entity.Task;
import com.github.sergeyingit.todolist.entity.User;
import com.github.sergeyingit.todolist.service.TaskService;
import com.github.sergeyingit.todolist.service.UserService;
import com.github.sergeyingit.todolist.service.email.EmailService;
import com.github.sergeyingit.todolist.service.pdf.PdfGenerationService;
import com.github.sergeyingit.todolist.service.security.AccessType;
import com.github.sergeyingit.todolist.service.security.CheckingUserRights;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PdfGenerationService pdfGenerationService;

    @Autowired
    private CheckingUserRights checkingUserRights;

    @GetMapping("/{userId}/tasks")
    public String getUserPage(@PathVariable("userId") int userId, Model model, Authentication authentication) {
//        String name = authentication.getName();
//        User currentUser = userService.findByEmail(name);
//        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(a-> a.getAuthority().equals("ROLE_ADMIN"));

        User requestedUser = userService.findById(userId);


//        if (currentUser != null && currentUser.equals(requestedUser) || isAdmin && requestedUser != null) {
//            model.addAttribute("user", requestedUser);
//            return "user";
//        } else if (currentUser != null && requestedUser != null) {
//            return "redirect:/error403";
//        }
        AccessType accessType = checkingUserRights.getAccessType(authentication, userId);

        switch (accessType) {
            case ALLOWED:
                model.addAttribute("user", requestedUser);
                return "user";
            case DENIED:
                return "redirect:/error403";
            default:
                return "redirect:/error404";
        }

//        return "redirect:/error404";
    }

    @GetMapping("/{userId}/tasks/create")
    public String getCreateTaskPage(@PathVariable("userId") int userId, Authentication authentication, Model model) {

        AccessType accessType = checkingUserRights.getAccessType(authentication, userId);

        switch (accessType) {
            case ALLOWED:
                Task task = new Task();
                task.setUserId(userId);
                model.addAttribute("task", task);
                return "user-task-info";
            case DENIED:
                return "redirect:/error403";
            default:
                return "redirect:/error404";
        }
    }


    @PostMapping("/{userId}/tasks/saveTask")
    public String saveTask(@PathVariable("userId") int userId, @Valid @ModelAttribute("task") Task task, BindingResult bindingResult, Authentication authentication) {

        AccessType accessType = checkingUserRights.getAccessType(authentication, userId);

        switch (accessType) {
            case ALLOWED:
                if (bindingResult.hasErrors()) {
                    return "user-task-info";
                }

                taskService.saveTask(task);
                return "redirect:/user/" + userId + "/tasks";
            case DENIED:
                return "redirect:/error403";
            default:
                return "redirect:/error404";
        }
    }

    @GetMapping("/{userId}/tasks/delete")
    public String deleteTask(@PathVariable("userId") int userId, @RequestParam("taskId") int taskId, Authentication authentication) {

        AccessType accessType = checkingUserRights.getAccessType(authentication, userId);

        switch (accessType) {
            case ALLOWED:
                taskService.deleteTask(taskId);
                return "redirect:/user/" + userId  + "/tasks";
            case DENIED:
                return "redirect:/error403";
            default:
                return "redirect:/error404";
        }
    }

    @GetMapping("/{userId}/tasks/update")
    public String updateTask(@PathVariable("userId") int userId, @RequestParam("taskId") int taskId, Model model, Authentication authentication) {

        AccessType accessType = checkingUserRights.getAccessType(authentication, userId);

        switch (accessType) {
            case ALLOWED:
                Task task = taskService.findById(taskId);
                model.addAttribute("task", task);
                return "user-task-info";
            case DENIED:
                return "redirect:/error403";
            default:
                return "redirect:/error404";
        }
    }

    @GetMapping(value = "/{userId}/tasks/pdf")
    @ResponseBody
    public ResponseEntity<InputStreamResource> createPDF(@PathVariable("userId") int userId, Authentication authentication) throws IOException {

        AccessType accessType = checkingUserRights.getAccessType(authentication, userId);

        switch (accessType) {
            case ALLOWED:
                User requestedUser = userService.findById(userId);
                List<Task> tasks = requestedUser.getTasks();

                InputStream inputStream = pdfGenerationService.prepareInputStrimPdf(tasks);
                return ResponseEntity.ok().contentType(APPLICATION_PDF).body(new InputStreamResource(inputStream));
            case DENIED:
                return ResponseEntity.status(301).header("location", "/error403").body(null);
            default:
                return ResponseEntity.status(301).header("location", "/error404").body(null);
        }
    }
}
