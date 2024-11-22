package com.employeeweb.app.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.employeeweb.app.entities.Job;

public interface JobRepository extends CrudRepository<Job,Long>{

    
    @Query("select j.id from Job j where j.id = ?1")
    List<Integer> findById(Integer id);

    

}
