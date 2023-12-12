package ua.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import ua.training.model.Language;
import ua.training.repository.LanguageRepository;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.Optional;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public Optional<Language> findByName(String name) {
        return languageRepository.findByName(name);
    }

    public Language getCurrentLanguage() {
        Locale locale = LocaleContextHolder.getLocale();
        return findByName(locale.getLanguage())
                .orElseThrow(() -> new RuntimeException("There is no such language"));
    }

//    public Locale
//    @PostConstruct
//    private void initLanguages() {
//        Language uk = new Language("uk");
//        languageRepository.save(uk);
//
//        Language en = new Language("en");
//        languageRepository.save(en);
//    }
}