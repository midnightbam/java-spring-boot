package com.techup.spring_demo.controller;

import com.techup.spring_demo.entity.Note;
import com.techup.spring_demo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*") // ✅ อนุญาตให้ frontend เรียกใช้ได้
public class NoteController {
    
    @Autowired
    private NoteService noteService;
    
    // ✅ CREATE - POST /api/notes
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        try {
            Note createdNote = noteService.createNote(note);
            return new ResponseEntity<>(createdNote, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // ✅ READ ALL - GET /api/notes
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        try {
            List<Note> notes = noteService.getAllNotes();
            if (notes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(notes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // ✅ READ BY ID - GET /api/notes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") Long id) {
        Optional<Note> noteData = noteService.getNoteById(id);
        
        if (noteData.isPresent()) {
            return new ResponseEntity<>(noteData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // ✅ UPDATE - PUT /api/notes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable("id") Long id, @RequestBody Note note) {
        try {
            Note updatedNote = noteService.updateNote(id, note);
            return new ResponseEntity<>(updatedNote, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // ✅ DELETE - DELETE /api/notes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteNote(@PathVariable("id") Long id) {
        try {
            noteService.deleteNote(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}