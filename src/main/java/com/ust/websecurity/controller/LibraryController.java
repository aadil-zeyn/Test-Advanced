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

            return ResponseEntity.noContent().build();

    }
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("renew-user-subscription/{id}")
    public ResponseEntity<User> renewUserSubcription(@PathVariable Long id){

            return ResponseEntity.noContent().build();

    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@RequestBody User user){

            return ResponseEntity.notFound().build();

    }

    @PutMapping("/issue-book")
    public ResponseEntity<Issue> updateIssue(@RequestBody Issue issue){

            return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/usr/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){

     return        ResponseEntity.notFound().build();

    }

    @DeleteMapping("/issue/{id}")
    public ResponseEntity<String> deleteIssue(@PathVariable long id) {

      return  ResponseEntity.noContent().build();

    }
}
