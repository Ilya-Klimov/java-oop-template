package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.Book;
import com.epam.izh.rd.online.entity.SchoolBook;
import com.epam.izh.rd.online.repository.BookRepository;

public class SimpleSchoolBookService implements BookService {
    private BookRepository<SchoolBook> schoolBookBookRepository;
    private  AuthorService authorService;


    public SimpleSchoolBookService() {

    }

    public SimpleSchoolBookService(BookRepository<SchoolBook> schoolBookBookRepository, AuthorService authorService) {
        this.schoolBookBookRepository = schoolBookBookRepository;
        this.authorService = authorService;
    }

    @Override
    public boolean save(Book book) {
        Author author = findAuthorByBookName(book.getName());
        if (authorService.count() == 0){
            return  false;
        }
        if (schoolBookBookRepository.count() == 0){
            schoolBookBookRepository.save((SchoolBook)book);
            return true;
        }
        else {
            if (author != null) {
                schoolBookBookRepository.save((SchoolBook) book);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public Book[] findByName(String name) {
        return schoolBookBookRepository.findByName(name);
    }

    @Override
    public int getNumberOfBooksByName(String name) {
        return findByName(name).length;
    }

    @Override
    public boolean removeByName(String name) {
        return schoolBookBookRepository.removeByName(name);
    }

    @Override
    public int count() {
        return schoolBookBookRepository.count();
    }

    @Override
    public Author findAuthorByBookName(String name) {
       SchoolBook[] schoolBooks = schoolBookBookRepository.findByName(name);
       if (schoolBooks.length > 0) {
           return authorService.findByFullName(schoolBooks[0].getAuthorName(),schoolBooks[0].getAuthorLastName());
       }else {
           return null;
       }
    }
}
