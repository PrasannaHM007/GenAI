package com.tap.Servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;

@WebServlet("/Embedding")
public class Embedding extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String SUPABASE_URL = "https://hxozxdvifdtbboujyswy.supabase.co/rest/v1/embeddings";
    private static final String SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imh4b3p4ZHZpZmR0YmJvdWp5c3d5Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTU4MzcxNzMsImV4cCI6MjAzMTQxMzE3M30.akmOViLrdMhSVcjz11Aim0FSE_-1XL_r0FFlKV4tEME";

    private Gson gson;

    @Override
    public void init() throws ServletException {
        super.init();
        gson = new Gson();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get user input from the request parameter
        String userInput = req.getParameter("userInput");

        PrintWriter writer = resp.getWriter();

        // Load GloVe word vectors into a map
        Map<String, double[]> wordVectors = loadGloVeVectors();

        // Prepare to collect the vector data for storage
        Map<String, Object> record = new HashMap<>();
        record.put("text", userInput);
        double[] combinedVector = new double[100]; // Assuming 100-dimensional vectors

        // Get the vector representation for each word in the user input
        for (String word : userInput.split("\\s+")) {
            double[] vector = wordVectors.get(word.toLowerCase());
            if (vector != null) {
                // Aggregate the vectors (this is a simple way to handle multiple words)
                for (int i = 0; i < vector.length; i++) {
                    combinedVector[i] += vector[i];
                }
            }
        }

        // Normalize the combined vector (optional)
        double norm = 0;
        for (double v : combinedVector) {
            norm += v * v;
        }
        norm = Math.sqrt(norm);
        for (int i = 0; i < combinedVector.length; i++) {
            combinedVector[i] /= norm;
        }

        record.put("vector", combinedVector);

        // Store the vector in Supabase
        try {
            storeVectorInSupabase(record);
            writer.println("Vector data stored successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            writer.println("Error: " + e.getMessage());
        }
    }

    private static Map<String, double[]> loadGloVeVectors() {
        Map<String, double[]> wordVectors = new HashMap<>();
        try (InputStream inputStream = Embedding.class.getClassLoader().getResourceAsStream("glove.6B.100d.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                String word = parts[0];
                double[] vector = new double[parts.length - 1];
                for (int i = 1; i < parts.length; i++) {
                    vector[i - 1] = Double.parseDouble(parts[i]);
                }
                wordVectors.put(word, vector);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordVectors;
    }

    private void storeVectorInSupabase(Map<String, Object> record) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(SUPABASE_URL);
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Authorization", "Bearer " + SUPABASE_KEY);
            post.setHeader("apikey", SUPABASE_KEY);

            String json = gson.toJson(record);
            StringEntity entity = new StringEntity(json);
            post.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(post)) {
                if (response.getCode() != 201) {
                    throw new IOException("Failed to store vector in Supabase: " + response.getReasonPhrase());
                }
            }
        }
    }
}
