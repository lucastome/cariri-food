package com.caririfood.infrastructure.sevice;

import com.caririfood.domain.exception.EntityNotFoundException;
import com.caririfood.domain.model.Kitchen;
import com.caririfood.domain.model.Restaurant;
import com.caririfood.domain.repository.RestaurantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private KitchenService kitchenService;

    public List<Restaurant> list() {
        return this.restaurantRepository.list();
    }

    public Restaurant findById(Long id) {

        Restaurant restaurant = this.restaurantRepository.findById(id);

        if (Objects.isNull(restaurant)) {
            throw new EntityNotFoundException(String.format("Não existe Restaurante cadastrado com o código %d", id));
        }

        return restaurant;
    }

    public Restaurant save(Restaurant restaurant) {
        Kitchen kitchen = this.kitchenService.findById(restaurant.getKitchen().getId());
        restaurant.setKitchen(kitchen);
        return this.restaurantRepository.save(restaurant);
    }

    public Restaurant update(Long id, Restaurant newRestaurant) {
        Restaurant oldRestaurant = this.findById(id);
        BeanUtils.copyProperties(newRestaurant, oldRestaurant, "id");
        return this.save(oldRestaurant);
    }
}
