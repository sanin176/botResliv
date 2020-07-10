package com.resliv.telegrambot.my_bot.controller;

import com.resliv.telegrambot.my_bot.dao.CitiesRepository;
import com.resliv.telegrambot.my_bot.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class CityController {

    @Autowired
    private CitiesRepository citiesRepository;

    @RequestMapping(value = "/addCity", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addCity(@RequestBody City city) {
        city.setCreated_at(LocalDate.now());
        city.setUpdated_at(LocalDate.now());
        citiesRepository.save(city);
    }

    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public ResponseEntity<?> getCities() {
        Iterable<City> foundCities = citiesRepository.findAll();
        return (foundCities.spliterator().getExactSizeIfKnown() != 0 ?
                new ResponseEntity<>(foundCities, HttpStatus.OK) :
                new ResponseEntity<>("Database is empty.", HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = {"/city/{id}"}, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<?> getCityByID(@PathVariable("id") int id) {
        Optional<City> foundCity = citiesRepository.findById(id);
        return (foundCity.isPresent() ?
                new ResponseEntity<>(foundCity, HttpStatus.OK) :
                new ResponseEntity<>("Wrong index.", HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = {"/deleteCity/{id}"}, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteCity(@PathVariable("id") int id) {
        citiesRepository.deleteById(id);
    }

    @RequestMapping(value = {"/updateCity"}, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void putCity(@RequestBody City c) {
        c.setUpdated_at(LocalDate.now());
        citiesRepository.save(c);
    }
}
