package project.text;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/processing")
public class TextProcessorController {

    private final TextProcessingResultRepository textProcessingResultRepository;

    private final TextProcessorService textProcessorService;

    public TextProcessorController(TextProcessingResultRepository textProcessingResultRepository, TextProcessorService textProcessorService) {
        this.textProcessingResultRepository = textProcessingResultRepository;
        this.textProcessorService = textProcessorService;
    }

    @PostMapping
    public ResponseEntity<Map<String, UUID>> process(@RequestBody TextProcessingRequest request) {
        if (request.getText() == null || request.getOptions() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        UUID id = UUID.randomUUID();
        textProcessorService.process(id, request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Collections.singletonMap("id", id));
    }

    @GetMapping
    public Iterable<TextProcessingResult> getResults() {
        return textProcessingResultRepository.findAll();
    }

    @GetMapping("{id}")
    public TextProcessingResult getResult(@PathVariable String id) {
        return textProcessingResultRepository
                .findById(getUUID(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private UUID getUUID(String id) {
        try {
            return UUID.fromString(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

}
