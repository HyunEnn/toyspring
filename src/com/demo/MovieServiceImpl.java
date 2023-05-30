package com.demo;


import com.spring.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MovieServiceImpl implements MovieService{

    private ArrayList<MovieDto> db = new ArrayList<>(); // MovieDto를 통한 ArrayList db를 만든다.
    public MovieServiceImpl() {
        System.out.println("MovieServiceImpl 객체 생성");
        db.add(new MovieDto(5, "선다운", "movie5.jpg", "영화 내용 \n선다운"));
        db.add(new MovieDto(6, "노스맨", "movie6.jpg", "영화 내용 \n노스맨"));
        db.add(new MovieDto(7,  "콘서트", "movie7.jpg", "영화 내용 \n콘서트"));
        db.add(new MovieDto(8, "K클래식", "movie8.jpg", "영화 내용 \nK클래식"));
    }

    @Override
    public ArrayList<MovieDto> select() {
        return db;
    }; // select 호출 때 저장된 db값을 전부 리턴

    @Override
    public boolean update(MovieDto movie) {
        MovieDto tmp = db.stream().filter(m -> m.getIdx() == movie.getIdx()).findAny().get();
        tmp.setTitle(movie.getTitle());
        tmp.setImage(movie.getImage());
        tmp.setContent(movie.getContent());
        return true;
    }

    @Override
    public boolean insert(MovieDto movie) {
        db.add(movie);
        return true;
    }

    @Override
    public boolean delete(int idx) {
        db.remove(db.stream().filter(m -> m.getIdx() == idx).findAny().get());
        return true;
    }

    @Override
    public MovieDto read(int idx) {
        MovieDto ret = db.stream().filter(m -> m.getIdx() == idx).findAny().get() ;
        return ret;
    } // for문 대신 stream 필터를 사용해서 idx 값에 맞는 값을 전부 꺼냄
    @Override
    public int count() {
        return db.size();
    }

    @Override
    public MovieDto search(String title) {
        for (MovieDto movie : db) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }
//        for(MovieDto movie : db) {
//            if(movie.getTitle().startsWith(title.substring(0, 2))) {
//                result.add(movie);
//            }
//            if(movie.getTitle().equals(idx))
//                result.add(movie);
//        }
//
//        MovieDto tn = db.stream().filter(m -> m.getIdx() == idx).findAny().get();
//        return tn; // search(title) 의 값에 맞는 db 데이터를 찾아서 db 데이터를 전부 return
//    }

}
