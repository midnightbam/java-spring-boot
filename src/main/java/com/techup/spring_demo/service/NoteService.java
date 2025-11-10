package com.techup.spring_demo.service;

import com.techup.spring_demo.entity.Note;
import com.techup.spring_demo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    
    @Autowired
    private NoteRepository noteRepository;
    
    // ✅ Create - สร้าง Note ใหม่
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }
    
    // ✅ Read All - ดึง Note ทั้งหมด
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }
    
    // ✅ Read by ID - ดึง Note ตาม ID
    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }
    
    // ✅ Update - แก้ไข Note
    public Note updateNote(Long id, Note noteDetails) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found with id: " + id));
        
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());
        
        return noteRepository.save(note);
    }
    
    // ✅ Delete - ลบ Note
    public void deleteNote(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found with id: " + id));
        
        noteRepository.delete(note);
    }
}
