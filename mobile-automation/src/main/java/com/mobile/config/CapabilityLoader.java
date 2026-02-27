package com.mobile.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * CapabilityLoader
 *
 * Utility class responsible for loading capability configuration
 * from JSON files. This class is designed for enterprise-scale
 * automation frameworks where capabilities may vary by environment,
 * device type, or execution context.
 *
 * The loader performs:
 *  - File existence validation
 *  - JSON parsing using Gson
 *  - Clear error reporting for debugging and CI/CD pipelines
 *
 * This class is stateless and thread-safe.
 */
public final class CapabilityLoader {

    private CapabilityLoader() {
        // Prevent instantiation
    }

    /**
     * Loads a JSON capability file and returns it as a JsonObject.
     *
     * @param filePath Path to the JSON capability file
     * @return Parsed JsonObject containing capabilities
     */
    public static JsonObject load(String filePath) {
        try {
            Path path = Path.of(filePath);

            // Validate file existence
            if (!Files.exists(path)) {
                throw new FileNotFoundException(
                        "Capability file not found at: " + filePath
                );
            }

            // Parse JSON file into JsonObject
            return JsonParser.parseReader(new FileReader(filePath))
                    .getAsJsonObject();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to load capability file: " + filePath +
                    ". Ensure the file exists and contains valid JSON.",
                    e
            );
        }
    }
}