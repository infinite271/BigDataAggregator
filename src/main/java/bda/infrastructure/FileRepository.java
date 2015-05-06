package bda.infrastructure;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Repository
public class FileRepository implements PersistenceRepository {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(FileRepository.class);

    @Value("${aggregatedResultsOutput}")
    private String aggregatedResultsOutput;

    @Override
    public void save(String dataToSave) throws IOException {
        Path path = Paths.get(aggregatedResultsOutput);
        Files.write(path, dataToSave.getBytes());
        log.info(String.format("Aggregated Results successfully written to file: %s", aggregatedResultsOutput));
    }

}
