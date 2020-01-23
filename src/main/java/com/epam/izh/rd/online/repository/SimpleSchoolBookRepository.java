package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;
import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[0];
    @Override
    public boolean save(SchoolBook book) {

        SchoolBook[] mirrorSchoolBooks = new SchoolBook[schoolBooks.length];

       if (schoolBooks.length>0) {
           for (int i = 0; i < schoolBooks.length; i++) {
               mirrorSchoolBooks[i] = schoolBooks[i];
           }
           schoolBooks = new SchoolBook[schoolBooks.length + 1];

           for (int i = 0; i < schoolBooks.length - 1; i++) {
               schoolBooks[i] = mirrorSchoolBooks[i];
           }

           schoolBooks[schoolBooks.length - 1] = book;
       }
       else{
           schoolBooks = new SchoolBook[schoolBooks.length + 1];
           schoolBooks[schoolBooks.length - 1] = book;
       }
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] nameOfBook = new SchoolBook[0];
        int count = 0;
        for (int i = 0;i<schoolBooks.length;i++){
            if(schoolBooks[i].getName() == name){
               count++;
            }
        }
       nameOfBook = new SchoolBook[count];
        count = 0;
        for (int i = 0;i<schoolBooks.length;i++){
            if(schoolBooks[i].getName() == name){
                nameOfBook[count]= schoolBooks[i];
                count++;
            }
        }
        return nameOfBook;
    }

    @Override
    public boolean removeByName(String name) {
        int removeElements = findByName(name).length;
        int count = 0;
        SchoolBook[] removeBooks = new SchoolBook[schoolBooks.length-removeElements];
        if (removeElements !=0 ){
            for(int i = 0; i < schoolBooks.length; i++){
               if (schoolBooks[i].getName() != name){
                    removeBooks[count] = schoolBooks[i];
                    count++;
                }
            }
            schoolBooks = new SchoolBook[removeBooks.length];

            for(int i = 0; i < schoolBooks.length; i++){
                schoolBooks[i]=removeBooks[i];
            }
            return  true;
        }
        else{
            return false;
        }
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
