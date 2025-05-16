# 🎮 Juegos en Java: Ahorcado y Batalla Naval

## 📋 Contenido
- [Compilación y Ejecución](#-compilación-y-ejecución)
- [Menú Principal](#-menú-principal)
- [🪓 Cómo jugar Ahorcado](#-cómo-jugar-ahorcado)
- [⚓ Cómo jugar Batalla Naval](#-cómo-jugar-batalla-naval)
- [⚠️ Consideraciones importantes](#-consideraciones-importantes)

---

## 🛠️ Compilación y Ejecución

1. **Compilar todos los archivos**:
   ```bash
   javac *.java
   ```

2. **Ejecutar el programa principal**:
   ```bash
   java MenuPrincipal
   ```

---

## 🎮 Menú Principal

Al ejecutar el programa verás:
```text
=== Menu Principal ===
1. Jugar Ahorcado
2. Jugar Batalla Naval
3. Salir
Seleccione (1-3): 
```

**Instrucciones**:
1. Ingresa el número de la opción
2. Presiona `Enter`
3. Sigue las instrucciones específicas de cada juego

---

## 🪓 Cómo jugar Ahorcado

### 🔍 Objetivo
Adivinar la palabra secreta antes de agotar los 6 intentos permitidos.

### 🕹️ Modo de Juego
1. **Jugador 1** (Creador de la palabra):
   - Ingresa una palabra secreta (ej: `computadora`)
   - La pantalla se limpiará automáticamente

2. **Jugador 2** (Adivinador):
   ```text
   Palabra: _ _ _ _ _ _ _ _ _ _ 
   Intentos restantes: 6
   Ingresa una letra: 
   ```
   - Ingresa letras de una en una
   - Aciertos revelarán la letra en su posición
   - Errores reducirán tus intentos

### 🏆 Victoria/Derrota
```text
(: Felicidades! Has adivinado la palabra: computadora
```
```text
): Te quedaste sin intentos! La palabra era: elefante
```

---

## ⚓ Cómo jugar Batalla Naval

### 🌊 Objetivo
Hundir los 3 barcos del oponente antes que él destruya los tuyos.

### 🔫 Fase de Preparación
**Para cada jugador**:
1. Coloca 3 barcos en posiciones secretas:
   ```text
   Ingrese fila (0-4): 2
   Ingrese columna (0-4): 3
   ```
   - Tablero de 5x5 posiciones (0 a 4)
   - No puedes colocar barcos en la misma posición

### 💥 Fase de Batalla
**Durante tu turno**:
```text
=== TU TABLERO ===
 0 1 2 3 4
0 - B - X !
1 - - - - -
...

=== TABLERO DEL OPONENTE ===
 0 1 2 3 4
0 - ! - - -
1 - - X - -
...

Ingrese fila (0-4): 1
Ingrese columna (0-4): 2
```
- **Acierto** (`X`): ¡Disparas de nuevo!
- **Fallo** (`!`): Cambio de turno

### 🏁 Fin del Juego
```text
¡FELICIDADES JUGADOR!
¡HAS HUNDIDO TODOS LOS BARCOS ENEMIGOS!
```

---

## ⚠️ Consideraciones importantes

1. **Reglas de entrada**:
   - Solo letras individuales en Ahorcado
   - Solo números enteros para coordenadas
   - Mayúsculas/minúsculas no importan

2. **Multijugador**:
   - En Batalla Naval los jugadores deben alternar en la misma computadora
   - ¡No mires la pantalla cuando el oponente coloca barcos!

3. **Errores comunes**:
   - Si ves `InputMismatchException`: Asegúrate de ingresar números donde se requiera
   - Si la pantalla se desordena: Maximiza tu terminal

4. **Volver al menú**:
   ```text
   ¿Volver al menú principal? (si/no): si
   ```

¡Diviértete jugando!