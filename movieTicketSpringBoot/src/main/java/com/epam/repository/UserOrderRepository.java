package com.epam.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.epam.beans.UserOrders;
@Repository
public interface UserOrderRepository extends CrudRepository<UserOrders, Integer>{

}
