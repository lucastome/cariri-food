package com.caririfood.infrastructure.sevice;

import com.caririfood.domain.exception.EntityInUseException;
import com.caririfood.domain.exception.EntityNotFoundException;
import com.caririfood.domain.model.State;
import com.caririfood.domain.repository.StateRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public List<State> list() {
        return this.stateRepository.list();
    }

    public State findById(Long id) {

        State state = this.stateRepository.findById(id);

        if (Objects.isNull(state)) {
            throw new EntityNotFoundException(String.format("Não existe Estado cadastrado com o código %d", id));
        }

        return state;
    }

    public State save(State state) {
        return this.stateRepository.save(state);
    }

    public State update(Long id, State newState) {
        State oldState = this.findById(id);
        BeanUtils.copyProperties(newState, oldState, "id");
        return this.save(oldState);
    }

    public void delete(Long id) {
        try {
            stateRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Não existe um cadastro de estado com código %d", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Estado de código %d não pode ser removido, pois está em uso", id));
        }
    }
}
