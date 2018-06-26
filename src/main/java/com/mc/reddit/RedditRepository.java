package com.mc.reddit;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RedditRepository extends CrudRepository<Reddit, Long>{
    List<Reddit> findAllByOrderByDateDesc();
}
