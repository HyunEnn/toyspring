package com.demo;


import com.spring.Autowired;
import com.spring.Controller;
import com.spring.RequestMapping;
import com.spring.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BasicController {

    @Autowired
    private MovieService service;

    public BasicController() {
        System.out.println("BasicController 객체 생성");
    }

    @RequestMapping("/count")
    @ResponseBody
    public String count(Map<String, String> param, Map<String, Object> model) {
        return  "count=" + service.count();
    }

    @RequestMapping("/list")
    public String list(Map<String, String> param, Map<String, Object> model) {
        List<MovieDto> movies = service.select();
        for(int i = 0; i < movies.size(); i++) {
            model.put("idx" + i, movies.get(i).getIdx());
            model.put("title" + i, movies.get(i).getTitle());
            model.put("image" + i, movies.get(i).getImage());
        }
        return  "list";
    }

    @RequestMapping("/read")
    public String read(Map<String, String> param, Map<String, Object> model) {
        int idx = Integer.parseInt(param.get("idx"));

        MovieDto movie = service.read(idx);

        model.put("idx", movie.getIdx());
        model.put("title", movie.getTitle());
        model.put("image", movie.getImage());
        model.put("content", movie.getContent());
        return  "read";
    }

    @RequestMapping("/search")
    public String search(Map<String, String> param, Map<String, Object> model) throws UnsupportedEncodingException {
        String decodedTitle = URLDecoder.decode(param.get("title"), "UTF-8");
//        System.out.println(name);
//        List<MovieDto> searchMovie = service.search(s_idx);
//        System.out.println("searchMovie = " + searchMovie);
//        for(int i=0;i<searchMovie.size();i++) {
//            model.put("idx " + i, searchMovie.get(i).getIdx());
//            model.put("title " + i, searchMovie.get(i).getTitle());
//            model.put("image " + i, searchMovie.get(i).getImage());
//        }
        MovieDto movie = service.search(decodedTitle);
        model.put("idx", movie.getIdx());
        model.put("title", movie.getTitle());
        model.put("image", movie.getImage());
        return "search";
    }
//    @RequestMapping("/update")
//    public String update(MovieDto movie, HttpServlet ) {
//        service.update(movie);
//
//    }



}