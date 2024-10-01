package org.factoriaf5.digital_academy.funko_shop.news_letter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${api-endpoint}/news-letter")
public class NewLetterController {
    
    @Autowired
    private NewLetterService newLetterService;

    @PostMapping
    public ResponseEntity<NewLetterDTO> createnNewLetter(@Valid @RequestBody NewLetterDTO newLetterDTO) {

        NewLetterDTO newNewLetterDTO = newLetterService.createNewLetter(newLetterDTO);

        if (newNewLetterDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(newNewLetterDTO);
    }
}
