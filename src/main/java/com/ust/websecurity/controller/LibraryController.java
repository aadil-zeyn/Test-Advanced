package com.ust.websecurity.controller;

import com.ust.websecurity.entity.Issue;
import com.ust.websecurity.entity.User;
import com.ust.websecurity.exception.UserNotSubscribedException;
import com.ust.websecurity.repository.IssueRepository;
import com.ust.websecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class LibraryController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IssueRepository issueRepository;

    @PostMapping("/issue-book")
    public ResponseEntity<Issue> issuebook(@RequestBody Issue issue){
        final var user = userRepository.findById(issue.getUser().getId());
        if(user.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        if(user.get().getSubscribed()==false){
            throw new UserNotSubscribedException("fghj");
        }
        return ResponseEntity.ok().body(issueRepository.save(issue));
    }
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok().body(userRepository.save(user));
    }

    @GetMapping("renew-user-subscription/{id}")
    public ResponseEntity<User> renewUserSubcription(@PathVariable Long id){
        final var user = userRepository.findById(id);
        if(user.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        user.get().setSubscribed(true);
        return ResponseEntity.ok().body(userRepository.save(user.get()));
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User updated = null;
        Optional<User> u = userRepository.findById(user.getId());
        if(u.isPresent()){
            updated = u.get();
            updated.setId(user.getId());
            updated.setSubscribed(user.getSubscribed());
            updated.setName(user.getName());
            return ResponseEntity.ok(userRepository.save(updated));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/issue-book")
    public ResponseEntity<Issue> updateIssue(@RequestBody Issue issue){
        Issue issu = null;
        Optional<Issue> op = issueRepository.findById(issue.getId());
        if(op.isPresent()){
            issu = op.get();
            issu.setId(issue.getId());
            issu.setFine(issue.getFine());
            issu.setPeriod(issue.getPeriod());
            issu.setIssueDate(issue.getIssueDate());
            issu.setReturnDate(issue.getReturnDate());
            return ResponseEntity.ok(issueRepository.save(issu));
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/usr/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        Optional<User> usr = userRepository.findById(id);
        if(usr.isEmpty()){
            ResponseEntity.notFound().build();
        }
        else{
            userRepository.deleteById(id);
        }
        return ResponseEntity.ok("deleted");
    }

    @DeleteMapping("/issue/{id}")
    public ResponseEntity<String> deleteIssue(@PathVariable long id){
       Optional<Issue> op = issueRepository.findById(id);
       if(op.isEmpty()){
           ResponseEntity.noContent().build();
       }
       else{
           issueRepository.deleteById(id);
       }
        return ResponseEntity.ok("deleted");
    }

}
