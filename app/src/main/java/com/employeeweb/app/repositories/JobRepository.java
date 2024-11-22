package com.employeeweb.app.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.employeeweb.app.entities.Job;

/**
 * Repository interface for managing Job entities.
 * Extends CrudRepository to provide basic CRUD operations along with custom queries.
 */
@Repository
public interface JobRepository extends CrudRepository<Job,Long>{

     /**
     * Finds a job's ID by its primary key.
     *
     * @param id the ID of the job to search for.
     * @return a list containing the job ID, or an empty list if no job is found.
     */
    @Query("select j.id from Job j where j.id = ?1")
    List<Integer> findById(Integer id);

    

}
