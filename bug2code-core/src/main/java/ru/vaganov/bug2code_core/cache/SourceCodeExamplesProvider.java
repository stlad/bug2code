package ru.vaganov.bug2code_core.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SourceCodeExamplesProvider {
    private final ResourcePatternResolver resourcePatternResolver;

    /**
     * Скачивает все .txt файлы из classpath:resources/cache и объединяет их содержимое
     * если папка не существует или в ней нет .txt файлов - Исключение
     *
     * @return объединенная строка со всеми текстами
     */
    public String mergeAllTextFiles() {
        // Ищем все txt файлы в папке resources/cache
        Resource[] resources = null;
        try {
            resources = resourcePatternResolver.getResources(
                    "classpath:**/cache/*.txt"
            );

            if (resources.length == 0) {
                // Пробуем альтернативный путь
                resources = resourcePatternResolver.getResources(
                        "classpath*:**/cache/*.txt"
                );
            }

            if (resources.length == 0) {
                throw new RuntimeException("В папке resources/cache не найдено .txt файлов");
            }

            List<String> fileContents = new ArrayList<>();

            // Читаем содержимое каждого файла
            for (Resource resource : resources) {
                if (resource.exists() && resource.isReadable()) {
                    String content = readResourceContent(resource);
                    fileContents.add(content);
                }
            }

            // Объединяем все содержимое в одну строку
            return String.join("\n", fileContents);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Читает содержимое файла как строку
     */
    private String readResourceContent(Resource resource) throws IOException {
        try (var inputStream = resource.getInputStream()) {
            return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        }
    }
}
