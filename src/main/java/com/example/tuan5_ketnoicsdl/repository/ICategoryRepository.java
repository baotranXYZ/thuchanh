package com.example.tuan5_ketnoicsdl.repository;
import com.example.tuan5_ketnoicsdl.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
