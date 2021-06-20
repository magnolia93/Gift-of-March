package com.march.bundleservice.data;

import com.march.bundleservice.Bundle;
import org.springframework.data.repository.CrudRepository;

public interface BundleRepo extends CrudRepository<Bundle, Long> {
}
