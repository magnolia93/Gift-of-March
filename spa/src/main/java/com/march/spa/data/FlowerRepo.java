package com.march.spa.data;

import com.march.spa.Flower;
import org.springframework.data.repository.CrudRepository;

public interface FlowerRepo extends CrudRepository<Flower, Long> {
}
