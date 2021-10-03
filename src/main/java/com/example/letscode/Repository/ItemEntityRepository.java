package com.example.letscode.Repository;

import com.example.letscode.Domain.ItemEntity;
import com.example.letscode.Domain.User;
import org.springframework.data.repository.CrudRepository;

public interface ItemEntityRepository extends CrudRepository<ItemEntity, Long> {
    Iterable<ItemEntity> findByOwner(User user);
}
