# Proyecto Grafos 
 
## Descripción del Proyecto
 
Este proyecto es una aplicación de escritorio desarrollada en **Java con JavaFX** que permite crear, visualizar y analizar grafos de manera interactiva. A través de una interfaz gráfica amigable, el usuario puede gestionar múltiples grafos, agregar aristas con peso, cargar datos desde archivos CSV y ejecutar operaciones de análisis como el cálculo de grado, verificación de grafo simple y verificación de grafo completo.
 
---
 
## ¿Por qué escogimos esta implementación?
 
Usamos **Lista de Aristas** porque los nodos son identificados por nombre (no índices), lo que hace innecesaria una Matriz de Adyacencia, y porque el CSV se mapea directamente a la estructura sin transformación adicional.
 
---
 
## Cómo Ejecutar el Proyecto
 
### Requisitos
- Java 17 o superior
- JavaFX 25
- Maven
### Pasos
```bash
git clone https://github.com/andres-rengifo-code/Proyceto_Grafos.git
cd Proyceto_Grafos
./mvnw javafx:run
```
