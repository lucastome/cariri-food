package com.caririfood.infrastructure.sevice;

import com.caririfood.domain.exception.EntityInUseException;
import com.caririfood.domain.exception.EntityNotFoundException;
import com.caririfood.domain.model.Kitchen;
import com.caririfood.domain.repository.KitchenRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class KitchenService {

    @Autowired
    private KitchenRepository kitchenRepository;

    public List<Kitchen> list() {
        return this.kitchenRepository.list();
    }

    public Kitchen findById(Long id) {

        Kitchen kitchen = this.kitchenRepository.findById(id);

        if (Objects.isNull(kitchen)) {
            throw new EntityNotFoundException(String.format("Não existe cozinha cadastrada com o código %d", id));
        }

        return kitchen;
    }

    public Kitchen save(Kitchen kitchen) {
        return this.kitchenRepository.save(kitchen);
    }

    public Kitchen update(Long id, Kitchen newKitchen) {
        Kitchen oldKitchen = this.findById(id);
        BeanUtils.copyProperties(newKitchen, oldKitchen, "id");
        return this.kitchenRepository.save(oldKitchen);
    }

    public void delete(Long id) {
        try {
            this.kitchenRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Cozinha de códido %d não localizada", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Cozinha de código %d não pode ser removida pois está em uso", id));
        }
    }

}
