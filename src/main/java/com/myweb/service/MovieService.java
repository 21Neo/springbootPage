package com.myweb.service;

import com.myweb.domain.Movie;
import com.myweb.mapper.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true,propagation = Propagation.NOT_SUPPORTED)
public class MovieService {
    @Autowired
    private MovieMapper movieMapper;

    public List<Movie> find(){
        return movieMapper.find();
    }

    public Movie find(Integer id){
        return movieMapper.findById(id);
    }

    public List<Movie> find(String name, Date beginTime,Date endTime){
        return movieMapper.findByParam(name,beginTime,endTime);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int add(Movie movie){
        return movieMapper.add(movie);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int modify(Movie movie){
        return movieMapper.modify(movie);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int remove(Integer id){
        return movieMapper.remove(id);
    }
}
