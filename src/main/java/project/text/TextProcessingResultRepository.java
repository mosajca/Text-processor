package project.text;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TextProcessingResultRepository extends CrudRepository<TextProcessingResult, UUID> {

}
