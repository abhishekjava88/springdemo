package com.abhi.training.demo.controller;

import com.abhi.training.demo.model.JournalEntry;
import com.abhi.training.demo.service.JournalEntryService;
import com.abhi.training.demo.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JournalEntryController {
    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping("/getEntry/{userName}")
    public ResponseEntity <List<JournalEntry>> getAllJournalEntriesOfUSer(@PathVariable String userName){
        var user = userService.findByUserName(userName);
        return user.map(user1->ResponseEntity.ok(user1.getJournalEntries()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/publishEntry/{userName}")
    public void publishEntry(@RequestBody JournalEntry entry, @PathVariable String userName) {
         journalEntryService.saveJournalEntry(entry,userName);
    }

    @GetMapping("/getAll")
    public List<JournalEntry> getAll() {
        return journalEntryService.getAll();
    }

    @DeleteMapping("/deleteEntry/{userName}/{myId}")
    public void deleteJournalEntryById(@PathVariable String userName, @PathVariable ObjectId myId) {
         journalEntryService.deleteEntry(userName,myId);
    }

    @PutMapping("/updateEntry/{userName}/{id}")
    public void updateEntryById(@PathVariable String userName, @PathVariable ObjectId id, @RequestBody JournalEntry newEntry){
        JournalEntry old = journalEntryService.get(id).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isBlank() ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().isBlank() ? newEntry.getContent() : old.getContent());
        }
         journalEntryService.saveJournalEntry(old, userName);
    }
}
