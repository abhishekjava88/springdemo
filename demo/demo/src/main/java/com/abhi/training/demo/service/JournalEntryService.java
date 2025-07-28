package com.abhi.training.demo.service;

import com.abhi.training.demo.model.JournalEntry;
import com.abhi.training.demo.model.User;
import com.abhi.training.demo.repository.JournalEntryRepository;
import com.abhi.training.demo.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void saveJournalEntry(JournalEntry journalEntry, String userName){
        Optional<User> user = userRepository.findByUserName(userName);
        if(user.isPresent()){
            User user1 = user.get();
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry journalEntry1 = journalEntryRepository.save(journalEntry);
            user1.getJournalEntries().add(journalEntry1);
            user1.setUserName(null);
            userRepository.save(user1);
        }
    }

    public List<JournalEntry> getAll() {
       return journalEntryRepository.findAll();
    }

    public void deleteEntry(String userName, ObjectId myId) {
        Optional<User> user = userRepository.findByUserName(userName);
        if(user.isPresent()){
            User user1 = user.get();
            user1.getJournalEntries().removeIf(x -> x.getId().equals(myId));
            userRepository.save(user1);
            journalEntryRepository.deleteById(myId);
        }else{
            journalEntryRepository.deleteById(myId);
        }

    }

    public Optional<JournalEntry> get(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

}
