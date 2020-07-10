package com.resliv.telegrambot.my_bot.dao;

import com.resliv.telegrambot.my_bot.model.City;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CitiesRepository extends CrudRepository<City, Integer> {
    @Modifying
    @Transactional
    @Query(value = "SELECT c.description as description FROM city c WHERE c.city = ?1", nativeQuery = true)
    List<DescriptionByCity> getDescriptionByCity(String string);

    interface DescriptionByCity {
        String getDescription();
    }
}
