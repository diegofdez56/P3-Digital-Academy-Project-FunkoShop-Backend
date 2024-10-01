package org.factoriaf5.digital_academy.funko_shop.news_letter;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface NewLetterRepository extends JpaRepository<NewLetter, Long> {
    NewLetter findByCode(String code);
}
