package es.cursojava.mongodb.usuariosapp;

import java.util.Arrays;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.lt;

public class UsuariosApp {

    public static void main(String[] args) {

        // 🔗 1. Cadena de conexión
        // Local:
        // String uri = "mongodb://localhost:27017";
        // Atlas (pega aquí la tuya de Atlas):
        String uri = "mongodb+srv://nana2025:nana2025@cluster0.xiicii9.mongodb.net/?appName=Cluster0";

        // 2. Crear cliente
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            // 3. Obtener base de datos (por ejemplo, getafemongodb)
            MongoDatabase db = mongoClient.getDatabase("getafemongodb");

            // (Opcional) crear explícitamente la colección si quieres:
            // db.createCollection("usuarios");

            // 4. Obtener la colección "usuarios"
            // Si no existe, se crea automáticamente al primer insert
            MongoCollection<Document> usuarios = db.getCollection("usuarios");

//            // 5. Crear 3 usuarios
//            Document usuario1 = new Document("nombre", "Ana")
//                    .append("edad", 30)
//                    .append("dni", "12345678A");
//
//            Document usuario2 = new Document("nombre", "Luis")
//                    .append("edad", 12)
//                    .append("dni", "23456789B");
//
//            Document usuario3 = new Document("nombre", "María")
//                    .append("edad", 40)
//                    .append("dni", "34567890C");
//
//            // 6. Insertar los 3 de golpe
//            usuarios.insertMany(Arrays.asList(usuario1, usuario2, usuario3));
//
//            System.out.println("Usuarios insertados correctamente en la colección 'usuarios'.");
            
            // 6. VER TODO LO QUE HAY EN LA COLECCIÓN USUARIOS
            System.out.println("=== TODOS LOS USUARIOS ===");
            FindIterable<Document> todos = usuarios.find();
            for (Document d : todos) {
                System.out.println(d.toJson());
            }

            // 7. VER SOLO LOS MAYORES DE 20
            System.out.println("\n=== USUARIOS CON EDAD < 20 ===");
            FindIterable<Document> mayoresDe20 = usuarios.find(lt("edad", 20));
            for (Document d : mayoresDe20) {
                System.out.println(d.toJson());
            }
            
            
        }
        
        

        
    }
}
