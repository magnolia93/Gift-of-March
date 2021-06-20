package com.march.treeservice.data;

import com.march.treeservice.Flower;
import org.springframework.data.repository.CrudRepository;

public interface FlowerRepo extends CrudRepository<Flower, Long> {
}
