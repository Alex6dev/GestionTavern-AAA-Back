package aaa.tavern.utils;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.repository.CrudRepository;

public class ServiceUtil {
    private ServiceUtil() {

    }

    /**
     * Allows to find an object in the DB thanks to its id
     * 
     * @param <T>        object defined via the repository
     * @param repository Restoring the object you are looking for
     * @param id         the id of the object you are looking for
     * @return object found in the DB
     * @throws EntityNotFoundException if there is no object with this id in the DB
     */
    public static <T> T getEntity(CrudRepository<T, Integer> repository, Integer id) throws EntityNotFoundException {
        Optional<T> optEntity = repository.findById(id);

        if (optEntity.isEmpty())
            throw new EntityNotFoundException();

        return optEntity.get();
    }

}
