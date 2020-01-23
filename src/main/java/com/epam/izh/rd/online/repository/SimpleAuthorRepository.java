package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.lang.reflect.Array;


public class SimpleAuthorRepository implements AuthorRepository {
    
    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        Author[] mirrorAuthors = new Author[authors.length];
        if(authors.length>0) {
            for (Author name : authors
            ) {
                if (findByFullName(name.getName(), name.getLastName()) == findByFullName(author.getName(), author.getLastName())) {
                    return false;
                }
            }
            for (int i = 0; i < mirrorAuthors.length; i++) {
                mirrorAuthors[i] = authors[i];
            }
            authors = new Author[authors.length + 1];
            for (int i = 0; i < authors.length - 1; i++) {
                authors[i] = mirrorAuthors[i];
            }
            authors[authors.length - 1] = author;
            return true;
        }
        else{
            authors = new Author[authors.length + 1];
            authors[authors.length - 1] = author;
            return  true;
        }
    }

    @Override
    public Author findByFullName(String name, String lastName) {
        if (authors.length > 0) {
            for (Author author : authors
            ) {
                if ((author.getName() == name) && (author.getLastName() == lastName)) {
                    return author;
                }
            }
            return null;
        } else {
            return null;
        }
    }

    @Override
    public boolean remove(Author author) {
        if (authors.length > 0 ){
        Author[] mirrorAuthors = new Author[authors.length-1];
        int k = 0;
        for (int i = 0; i< authors.length;i++){
            if (findByFullName(authors[i].getName(),authors[i].getLastName()) == findByFullName(author.getName(),author.getLastName())){
                System.arraycopy(authors,i+1, authors,  i,authors.length-i-1);
            }else{
                return false;
            }
        }

        for (int i = 0; i<mirrorAuthors.length;i++){
            mirrorAuthors[i] = authors[i];
        }
        authors = new Author[mirrorAuthors.length];
        for (int i = 0; i<authors.length;i++) {
            authors[i] = mirrorAuthors[i];
        }
        return true;
        }else{
            return false;
        }
    }


    @Override
    public int count() {
        return authors.length;
    }
}
