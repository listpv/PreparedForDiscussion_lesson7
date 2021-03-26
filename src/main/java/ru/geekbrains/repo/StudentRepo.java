package ru.geekbrains.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entities.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    @Override
    void deleteById(Long aLong);

//    @Query(value = "UPDATE Student s SET s.name = :name, s.age = :age WHERE s.id = :id")
//    Student update(@Param("id") Long id, @Param("name") String name, @Param("age") Integer age);
}