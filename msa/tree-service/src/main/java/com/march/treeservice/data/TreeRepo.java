package com.march.treeservice.data;

import com.march.treeservice.Tree;
import org.springframework.data.repository.CrudRepository;

public interface TreeRepo extends CrudRepository<Tree, Long> {
}
