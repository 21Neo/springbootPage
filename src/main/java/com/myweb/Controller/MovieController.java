package com.myweb.Controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myweb.domain.CustomType;
import com.myweb.domain.Movie;
import com.myweb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value = "/movies",method = RequestMethod.GET)
    public ResponseEntity<?> getMovies(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "beginTime",required = false)
            @JsonFormat(pattern = "yyyy-MM-dd")Date beginTime,
            @RequestParam(value = "endTime",required = false)
            @JsonFormat(pattern = "yyyy-MM-dd")Date endTime
            ){
        CustomType customType = new CustomType(400,"查无数据");
        List<Movie> movies = movieService.find(name,beginTime,endTime);
        if(movies.size() == 0){
            return new ResponseEntity<>(customType, HttpStatus.OK);
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
}
