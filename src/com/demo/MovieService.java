package com.demo;

import com.spring.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface MovieService {
    public ArrayList<MovieDto> select();

    public boolean delete(int idx);

    public boolean insert(MovieDto movie);
    public boolean update(MovieDto movie);
    public MovieDto read(int idx);
    public int count();
    public MovieDto search(String title);
}
