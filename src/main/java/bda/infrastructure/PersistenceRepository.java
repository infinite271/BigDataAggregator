package bda.infrastructure;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface PersistenceRepository {

    void save(String dataToSave) throws IOException;

}
