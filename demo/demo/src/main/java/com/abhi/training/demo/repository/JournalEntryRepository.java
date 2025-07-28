package com.abhi.training.demo.repository;

import com.abhi.training.demo.model.JournalEntry;
import com.abhi.training.demo.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {


}
