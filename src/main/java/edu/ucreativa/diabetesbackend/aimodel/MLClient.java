import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class MLClient {

    private static final String API_URL = "https://github.com/FabsSWD/DiabetesAPI.git";  // URL de la API de predicción

    public String predict(String inputJson) {
        try {
            // Establecer la conexión con la URL del API
            URL url = new URL(API_URL + "/predict"); // Suposición: el endpoint de predicción es /predict
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            // Enviar el JSON de entrada al API
            OutputStream os = conn.getOutputStream();
            os.write(inputJson.getBytes());
            os.flush();

            // Leer la respuesta del API
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            StringBuilder response = new StringBuilder();
            while ((output = br.readLine()) != null) {
                response.append(output);
            }

            // Cerrar la conexión
            conn.disconnect();
            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
